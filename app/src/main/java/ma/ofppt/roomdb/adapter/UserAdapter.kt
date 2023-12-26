package ma.ofppt.roomdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ma.ofppt.roomdb.R
import ma.ofppt.roomdb.model.User

class UserAdapter(items : List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    var list :List<User> = items
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
var firstName:TextView=itemView.findViewById(R.id.firstName)
var lastName:TextView=itemView.findViewById(R.id.lastName)
var btnEdite:ImageView=itemView.findViewById(R.id.btnEdite)
var btnDelete:ImageView=itemView.findViewById(R.id.btnDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        var user=list[position]
        holder.firstName.text=user.firstName
        holder.lastName.text=user.lastName
    }

    override fun getItemCount(): Int {
        return list.size
    }
}