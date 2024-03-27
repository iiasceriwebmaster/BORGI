package md.webmaster.borgi.tools

object Extensions {
    fun Long.chunkedToString(chunkSize: Int = 4, separator: CharSequence = " "): String {
        return this.toString().chunked(chunkSize).joinToString(separator = separator)
    }
}