package ie.wit.chatup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_message_chat.*

class MessageChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_chat)

        send_message_btn.setOnClickListener {
            val message = text_message.text.toString()
            if (message == "")
            {
                Toast.makeText(this@MessageChatActivity, "Please write a message..", Toast.LENGTH_LONG).show()
            }
            else
            {
                sendMessageToUser()
            }
        }
    }
}
