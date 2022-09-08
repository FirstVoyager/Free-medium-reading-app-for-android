package limitless.android.freemediumreadingapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.textview.MaterialTextView
import limitless.android.freemediumreadingapp.R
import limitless.android.freemediumreadingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var textViewListener = View.OnClickListener {
        val tag = "https://medium.com/tag/${(it as MaterialTextView).text.toString().lowercase().replace(" ", "-")}"
        read(tag)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.medium.setOnClickListener{
            read("https://medium.com/")
        }
        binding.startup.setOnClickListener(textViewListener)
        binding.life.setOnClickListener(textViewListener)
        binding.lifeLessons.setOnClickListener(textViewListener)
        binding.politics.setOnClickListener(textViewListener)
        binding.travel.setOnClickListener(textViewListener)
        binding.poetry.setOnClickListener(textViewListener)
        binding.entrepreneurship.setOnClickListener(textViewListener)
        binding.education.setOnClickListener(textViewListener)
        binding.health.setOnClickListener(textViewListener)
        binding.love.setOnClickListener(textViewListener)
        binding.design.setOnClickListener(textViewListener)
        binding.writing.setOnClickListener(textViewListener)
        binding.technology.setOnClickListener(textViewListener)
        binding.selfImprovement.setOnClickListener(textViewListener)
        binding.business.setOnClickListener(textViewListener)
        binding.music.setOnClickListener(textViewListener)
        binding.socialMedia.setOnClickListener(textViewListener)
        binding.sports.setOnClickListener(textViewListener)
        binding.food.setOnClickListener(textViewListener)
        binding.art.setOnClickListener(textViewListener)
        binding.byArticle.setOnClickListener{
            if (binding.innerArticle.visibility == View.GONE) {
                binding.ivArticle.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                binding.innerArticle.visibility = View.VISIBLE
            }else{
                binding.ivArticle.setImageResource(R.drawable.ic_baseline_navigate_next_24)
                binding.innerArticle.visibility = View.GONE
            }
        }
        binding.byCategory.setOnClickListener{
            if (binding.innerCategory.visibility == View.GONE) {
                binding.ivCategory.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                binding.innerCategory.visibility = View.VISIBLE
            }else{
                binding.ivCategory.setImageResource(R.drawable.ic_baseline_navigate_next_24)
                binding.innerCategory.visibility = View.GONE

            }
        }
        binding.btnRead.setOnClickListener{
            if (binding.editText.text.toString().lowercase().startsWith("https://medium.com/")) {
                read(binding.editText.text.toString().trim().lowercase())
            }else{
                Toast.makeText(this, getString(R.string.please_enter_valid_link), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun read(urlLink: String) {
        val medium = Intent(this, MediumActivity::class.java)
        medium.putExtra(MediumActivity.url, urlLink)
        startActivity(medium)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
