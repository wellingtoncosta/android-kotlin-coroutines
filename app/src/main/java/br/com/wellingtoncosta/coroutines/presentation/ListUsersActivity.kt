package br.com.wellingtoncosta.coroutines.presentation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import br.com.wellingtoncosta.coroutines.R
import br.com.wellingtoncosta.coroutines.databinding.ActivityListUsersBinding
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
        setupToolbar()
        setupRecyclerView()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.toolbar_title)
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        val orientation = layoutManager.orientation

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, orientation))
        binding.recyclerView.adapter = ListUsersAdapter()
        binding.swipe.setOnRefreshListener { viewModel.loadUsers() }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadUsers()
        observeLoadingStatus()
        observerUsers()
    }

    private fun observeLoadingStatus() {
        viewModel.loadingStatus.observe(
                this,
                Observer { isLoading -> binding.swipe.isRefreshing = isLoading ?: false })
    }

    private fun observerUsers() {
        viewModel.users.observe(
                this,
                Observer { users ->
                    val adapter = binding.recyclerView.adapter as ListUsersAdapter
                    adapter.users = users!!
                }
        )
    }

}