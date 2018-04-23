package br.com.wellingtoncosta.coroutines.presentation

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.wellingtoncosta.coroutines.databinding.ListUsersItemBinding

/**
 * @author wellingtoncosta on 23/04/18
 */
class ListUsersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding: ListUsersItemBinding = DataBindingUtil.bind(view)!!

}