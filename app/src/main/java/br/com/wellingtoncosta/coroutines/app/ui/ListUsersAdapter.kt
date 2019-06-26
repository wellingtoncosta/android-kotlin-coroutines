package br.com.wellingtoncosta.coroutines.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.wellingtoncosta.coroutines.R
import br.com.wellingtoncosta.coroutines.databinding.ListUsersItemBinding
import br.com.wellingtoncosta.coroutines.domain.model.User
import com.facebook.drawee.generic.RoundingParams

/**
 * @author Wellington Costa on 23/04/18
 */
class ListUsersAdapter(
    private val users: List<User>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListUsersViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_users_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ListUsersViewHolder).binding
        val user = users[position]
        loadImage(binding, user)
        binding.user = user
    }

    private fun loadImage(binding: ListUsersItemBinding, user: User) {
        val roundingParams = RoundingParams.fromCornersRadius(5f)
        roundingParams.roundAsCircle = true
        binding.image.hierarchy.roundingParams = roundingParams
        binding.image.setImageURI(user.avatarUrl)
    }

    override fun getItemCount() = users.size

}

class ListUsersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding: ListUsersItemBinding = ListUsersItemBinding.bind(view)

}