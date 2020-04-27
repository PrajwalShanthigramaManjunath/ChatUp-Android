package ie.wit.chatup.AdapterClasses

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import ie.wit.chatup.ModelClasses.Users
import ie.wit.chatup.R

class UserAdapter (
    mContext: Context,
    mUsers: List<Users>,
    isChatCheck: Boolean
) : RecyclerView.Adapter<UserAdapter.ViewHolder?>()
    {
        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
        {
            var userNameTxt: TextView
            var profileImageView: CircleImageView
            var onlineTextView: TextView                              // online text view
            var ofllineTextView: TextView                            // offline text view
            var lastMessageTxt: TextView
init{
    userNameTxt = itemView.findViewById(R.id.username)
    userNameTxt = itemView.findViewById(R.id.username)
    userNameTxt = itemView.findViewById(R.id.username)
    userNameTxt = itemView.findViewById(R.id.username)
    userNameTxt = itemView.findViewById(R.id.username)

}
        }
    }