package com.kotlin.goods

import android.widget.EditText
import org.jetbrains.anko.find
import ren.qinc.numberbutton.NumberButton
import ren.qinc.numberbutton.R
fun NumberButton.getEditText():EditText{
    return find(R.id.text_count)
}
