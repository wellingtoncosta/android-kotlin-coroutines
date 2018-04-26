package br.com.wellingtoncosta.coroutines.presentation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import br.com.wellingtoncosta.coroutines.R
import br.com.wellingtoncosta.coroutines.databinding.ActivityListUsersBinding
import com.miguelcatalan.materialsearchview.MaterialSearchView
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * @author wellingtoncosta on 22/04/18
 */
class ListUsersActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ListUsersViewModel

    private lateinit var binding: ActivityListUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListUsersViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_users)
        setupSearchView()
        setupRecyclerView()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        observeLoadingStatus()
        observerUsers()
        observeErrorMessage()
        viewModel.getAll()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.action_search)
        binding.includeToolbar?.searchView?.setMenuItem(item)
        return true
    }

    private fun setupSearchView() {
        setSupportActionBar(binding.includeToolbar?.toolbar)
        binding.includeToolbar?.searchView?.setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewShown() { }

            override fun onSearchViewClosed() {

            }
        })

        binding.includeToolbar?.searchView?.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getByUsername(query)
                return true
            }

            override fun onQueryTextChange(newText: String) = true
        })
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        val orientation = layoutManager.orientation

        binding.includeContent?.recyclerView?.setHasFixedSize(true)
        binding.includeContent?.recyclerView?.layoutManager = layoutManager
        binding.includeContent?.recyclerView?.addItemDecoration(DividerItemDecoration(this, orientation))
        binding.includeContent?.recyclerView?.adapter = ListUsersAdapter()
        binding.includeContent?.swipe?.setOnRefreshListener { viewModel.getAll() }
    }

    private fun observeLoadingStatus() {
        viewModel.loadingStatus.observe(
                this,
                Observer { isLoading -> binding.includeContent?.swipe?.isRefreshing = isLoading ?: false })
    }

    private fun observerUsers() {
        viewModel.users.observe(
                this,
                Observer { users ->
                    val adapter = binding.includeContent?.recyclerView?.adapter as ListUsersAdapter
                    adapter.users = users!!
                }
        )
    }

    private fun observeErrorMessage() {
        viewModel.errorMessage.observe(
                this,
                Observer { errorMessage ->
                    if(errorMessage != null && errorMessage != -1) {
                        Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
                    }
                })
    }

}