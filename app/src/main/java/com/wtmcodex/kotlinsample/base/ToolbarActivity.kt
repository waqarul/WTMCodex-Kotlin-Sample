package com.wtmcodex.kotlinsample.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import com.wtmcodex.kotlinsample.R
import com.wtmcodex.kotlinsample.home.HomeActivity


abstract class ToolbarActivity : BaseActivity() {
    // fields

    // keys
    open val EXTRA_OPERATION = "EXTRA_OPERATION"
    open val EXTRA_CHANGE_LANGUAGE = "EXTRA_CHANGE_LANGUAGE"
    open val EXTRA_CHANGE_LANGUAGE_VALUE = "EXTRA_CHANGE_LANGUAGE_VALUE"

    private lateinit var mSearchBox: EditText
    private lateinit var mLanguageBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set toolbar
        val toolbar: Toolbar = findViewById(R.id.tool_bar)
        setSupportActionBar(toolbar)

        // set action tool bar
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(false)


    }

    override fun setListeners() {

        mSearchBox = findViewById(R.id.search_box_text)
        mLanguageBtn = findViewById(R.id.language_btn)

        // setting language button text
        if ("fi".equals(mLanguageManager.mCurrentLanguage)) {
            mLanguageBtn.setText(R.string.english_lbl)
        } else {
            mLanguageBtn.setText(R.string.finnish_lbl)
        }

        // adding action for keyboard search
        mSearchBox.setOnEditorActionListener() { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onSearch(getSearchString())
                true
            } else {
                false
            }
        }

        // adding watcher when text is entered
        mSearchBox.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                onSearch("" + p0)
            }
        })

        // change language on button click
        mLanguageBtn.setOnClickListener(View.OnClickListener { view ->
            if ("fi".equals(mLanguageManager.mCurrentLanguage)) {
                informHomeOfLanguageChange("en")
            } else {
                informHomeOfLanguageChange("fi")
            }
        })
    }


    // derive classes will override this method, so that they get query to search
    protected abstract fun onSearch(query: String)

    // inform home activity that language has been changed
    private fun informHomeOfLanguageChange(language: String) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.putExtra(EXTRA_OPERATION, EXTRA_CHANGE_LANGUAGE)
        intent.putExtra(EXTRA_CHANGE_LANGUAGE_VALUE, language)
        startActivity(intent)
        finish()
    }

    // notify derive class fot search
    protected fun getSearchString(): String {
        return mSearchBox.getText().toString()
    }


    override fun getLayoutResourceId(): Int {
        return R.layout.app_bar_main
    }
}
