package com.example.usershilt.features.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.usershilt.databinding.UsersItemsBinding
import com.example.usershilt.networks.models.UserModel

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    private val usersList = mutableListOf<UserModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UsersItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(usersList[position])
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun submitList(users: List<UserModel>) {
        usersList.clear()
        usersList.addAll(users)
        notifyDataSetChanged()
    }

    class UserViewHolder(
        private val binding: UsersItemsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserModel) = with(binding) {
            idTv.text = user.id.toString()
            nameTv.text = user.name
            usernameTv.text = user.username
            emailTv.text = user.email
            streetTv.text = user.address.street
            suiteTv.text = user.address.suite
            cityTv.text = user.address.city
            zipcodeTv.text = user.address.zipcode
            latTv.text = user.address.geo.lat
            lonTv.text = user.address.geo.lng
            phoneTv.text = user.phone
            companyNameTv.text = user.company.name
            catchPhraseTv.text = user.company.catchPhrase
            bsTv.text = user.company.bs
        }
    }
}
