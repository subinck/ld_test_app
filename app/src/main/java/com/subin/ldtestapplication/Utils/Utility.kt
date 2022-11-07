package com.subin.ldtestapplication.Utils

import android.text.Html

class Utility {
    companion object{
        fun html2text(html: String?): String? {
            return Html.fromHtml(html).toString()
        }
    }
}