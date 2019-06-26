package br.com.wellingtoncosta.coroutines.app.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.wellingtoncosta.coroutines.R
import com.miguelcatalan.materialsearchview.MaterialSearchView
import org.koin.androidx.viewmodel.ext.android.viewModel
import br.com.wellingtoncosta.coroutines.databinding.ActivityListUsersBinding as Binding

/**
 * @author Wellington Costa on 22/04/18
 */
class ListUsersActivity : AppCompatActivity() {

    private val viewModel by viewModel<ListUsersViewModel>()

    private val binding by lazy {
        DataBindingUtil.setContentView<Binding>(this, R.layout.activity_list_users)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        observeUsers()
        observeError()

        setupSearchView()
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.action_search)
        binding.searchView.setMenuItem(item)
        return true
    }

    override fun onBackPressed() {
        if (binding.searchView.isSearchOpen) {
            binding.searchView.closeSearch()
        } else {
            super.onBackPressed()
        }
    }

    private fun setupSearchView() {
        setSupportActionBar(binding.toolbar)
        binding.searchView.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewShown() { }
            override fun onSearchViewClosed() { viewModel.getAll() }
        })

        binding.searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getByUsername(query)
                return true
            }

            override fun onQueryTextChange(newText: String) = true
        })
    }

    private fun setupRecyclerView() {
        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerView.context,
            (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
        )
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun observeUsers() {
        viewModel.users.observe(this,
                Observer { users -> binding.recyclerView.adapter =
                    ListUsersAdapter(users)
                }
        )
    }

    private fun observeError() {
        viewModel.error.observe(
                this,
                Observer { t ->
                    Log.e("LOAD_USERS", "$t")
                })
    }

}