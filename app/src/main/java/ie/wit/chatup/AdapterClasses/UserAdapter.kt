package ie.wit.chatup.AdapterClasses

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import ie.wit.chatup.MessageChatActivity
import ie.wit.chatup.ModelClasses.Users
import ie.wit.chatup.R

class UserAdapter (
    mContext: Context,
    mUsers: List<Users>,
    isChatCheck: Boolean
) : RecyclerView.Adapter<UserAdapter.ViewHolder?>()
    {
        private val mContext: Context
        private val mUsers: List<Users>
        private var isChatCheck: Boolean

        init {
            this.mUsers = mUsers
            this.mContext = mContext
            this.isChatCheck = isChatCheck
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val view: View = LayoutInflater.from(mContext).inflate(R.layout.user_search_item_layout, viewGroup, false)
            return UserAdapter.ViewHolder(view)

        }

        override fun getItemCount(): Int {
return mUsers.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val user: Users = mUsers[position]
            holder.userNameTxt.text = user!!.getUserName()
            Picasso.get().load(user.getProfile()).placeholder(R.drawable.profile).into(holder.profileImageView)

            holder.itemView.setOnClickListener {
                val options = arrayOf<CharSequence>(
                    "Send Message",
                    "Visit Profile"
                )
                val builder: AlertDialog.Builder = AlertDialog.Builder(mContext)
                builder.setTitle("What do you want?")
                builder.setItems(options, DialogInterface.OnClickListener { dialog, position ->
                    if (position == 0)
                    {
                        val intent = Intent(mContext, MessageChatActivity::class.java)
                        intent.putExtra("visit_id", user.getUID())
                        mContext.startActivity(intent)
                    }
                    if (position == 1)
                    {

                    }
                })
                builder.show()

            }
        }


        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
        {
            var userNameTxt: TextView
            var profileImageView: CircleImageView
            var onlineImageView: CircleImageView                              // online view
            var ofllineImageView: CircleImageView                            // offline view
            var lastMessageTxt: TextView
init{
    userNameTxt = itemView.findViewById(R.id.username)
    profileImageView = itemView.findViewById(R.id.profile_image)
    onlineImageView = itemView.findViewById(R.id.image_online)
    ofllineImageView = itemView.findViewById(R.id.image_offline)
    lastMessageTxt = itemView.findViewById(R.id.message_last)

}
        }


    }