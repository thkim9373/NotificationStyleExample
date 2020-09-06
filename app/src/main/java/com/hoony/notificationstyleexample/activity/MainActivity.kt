package com.hoony.notificationstyleexample.activity

import android.app.NotificationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import androidx.core.graphics.drawable.IconCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.hoony.notificationstyleexample.R
import com.hoony.notificationstyleexample.databinding.ActivityMainBinding
import com.hoony.notificationstyleexample.fragment.BigPictureFragment
import com.hoony.notificationstyleexample.fragment.BigTextFragment
import com.hoony.notificationstyleexample.fragment.MessagingFragment
import com.hoony.notificationstyleexample.notification.NotificationHelper
import com.hoony.notificationstyleexample.util.VersionUtil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var nowFragmentName: String = ""
    private val bigTextFragment: BigTextFragment = BigTextFragment()
    private val bigPictureFragment: BigPictureFragment = BigPictureFragment()
    private val messageFragment: MessagingFragment = MessagingFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setListener()
    }

    private fun setListener() {
        binding.create.setOnClickListener {
            NotificationHelper.sendNotification(
                this,
                getNotificationImportance(),
                false,
                getNotificationStyle(binding.styleGroup.checkedRadioButtonId),
                getNotificationTitle(),
                getNotificationText()
            )
        }
        binding.styleGroup.setOnCheckedChangeListener { _, checkedId ->
            val fragment = getFragment(checkedId)
            if (fragment != null) {
                nowFragmentName = fragment.toString()
                supportFragmentManager.beginTransaction()
                    .replace(binding.customItemLayout.id, fragment, nowFragmentName).commit()
            } else {
                val nowFragment = supportFragmentManager.findFragmentByTag(nowFragmentName)
                if (nowFragment != null && nowFragment.isVisible) {
                    supportFragmentManager.beginTransaction().remove(nowFragment).commit()
                }
            }
        }
    }

    private fun getNotificationTitle(): String =
        if (binding.titleEdit.text.isNotEmpty()) {
            binding.titleEdit.text.toString()
        } else {
            "Title"
        }

    private fun getNotificationText(): String =
        if (binding.textEdit.text.isNotEmpty()) {
            binding.textEdit.text.toString()
        } else {
            "Text"
        }

    private fun getFragment(checkedId: Int): Fragment? =
        when (checkedId) {
            R.id.defaultStyle -> null
            R.id.bigTextStyle -> bigTextFragment
            R.id.bigPictureStyle -> bigPictureFragment
            R.id.inboxStyle -> null
            R.id.messagingStyle -> messageFragment
            R.id.mediaStyle -> null
            else -> null
        }

    private fun getNotificationStyle(checkedId: Int): NotificationCompat.Style? =
        when (checkedId) {
            R.id.defaultStyle -> null
            R.id.bigTextStyle -> {
                NotificationCompat.BigTextStyle()
                    .bigText(bigTextFragment.getBigText())
            }
            R.id.bigPictureStyle -> {
                NotificationCompat.BigPictureStyle()
                    .bigPicture(bigPictureFragment.getImageBitmap())
                    .bigLargeIcon(bigPictureFragment.getImageBitmap())
            }
            R.id.inboxStyle -> {
                NotificationCompat.InboxStyle()
                    .addLine("Mail1 ...")
                    .addLine("Mail2 ...")
                    .addLine("Mail3 ...")
                    .setBigContentTitle("Big content title")
                    .setSummaryText("Summary text")
            }
            R.id.messagingStyle -> {
//                https://picsum.photos/id/237/200/300
                val userIcon1 = IconCompat.createWithResource(this, R.drawable.lion)
                val userIcon2 = IconCompat.createWithResource(this, R.drawable.bird)
                val userIcon3 = IconCompat.createWithResource(this, R.drawable.deer)
                val userName1 = "Person 1"
                val userName2 = "Person 2"
                val userName3 = "Person 3"
                val timeStamp = System.currentTimeMillis()
                val user1 = Person.Builder().setIcon(userIcon1).setName(userName1).build()
                val user2 = Person.Builder().setIcon(userIcon2).setName(userName2).build()
                val user3 = Person.Builder().setIcon(userIcon3).setName(userName3).build()

                NotificationCompat.MessagingStyle(user3)
                    .addMessage("Message 1", timeStamp, user1)
                    .addMessage("Message 2", timeStamp, user2)
            }
//                R.id.mediaStyle -> androidx.media.app.NotificationCompat.MediaStyle()
            else -> null
        }

    private fun getNotificationImportance(): Int {
        if (VersionUtil.isLessThanN()) return -1
        return if (binding.headUpSwitch.isChecked) {
            NotificationManager.IMPORTANCE_HIGH
        } else {
            NotificationManager.IMPORTANCE_DEFAULT
        }
    }
}