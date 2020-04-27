package ie.wit.chatup.AdapterClasses

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ie.wit.chatup.ModelClasses.Users

class UserAdapter (
    mContext: Context,
    mUsers: List<Users>,
    isChatCheck: Boolean
) : RecyclerView.Adapter<UserAdapter.ViewHolder?>()
    {
        class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
        {
            var userNameTxt: TextView
            var userNameTxt: TextView
            var userNameTxt: TextView
            var userNameTxt: TextView
            var userNameTxt: TextView

        }
    }