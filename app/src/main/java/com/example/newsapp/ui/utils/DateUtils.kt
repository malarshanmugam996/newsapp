package com.example.newsapp.ui.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(isoDate: String?): String {
    return try {
        val date = OffsetDateTime.parse(isoDate)
        date.format(
            DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.getDefault())
        )
    } catch (e: Exception) {
        isoDate ?: ""   // ✅ fallback
    }
}
