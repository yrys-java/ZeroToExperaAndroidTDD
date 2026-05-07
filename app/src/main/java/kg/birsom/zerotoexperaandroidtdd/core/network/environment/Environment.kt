package kg.birsom.zerotoexperaandroidtdd.core.network.environment

data class Environment(
    val baseAddress: String,
    val isSslEnabled: Boolean = true
) {
    val restAddress: String
        get() {
            val normalizedBaseAddress = baseAddress.trim().removeSuffix("/")
            val hasScheme = normalizedBaseAddress.startsWith("http://") ||
                normalizedBaseAddress.startsWith("https://")

            return if (hasScheme) {
                "$normalizedBaseAddress/"
            } else {
                "${if (isSslEnabled) "https" else "http"}://$normalizedBaseAddress/"
            }
        }

    companion object {

        val Default = Environment(
            baseAddress = "jsonplaceholder.typicode.com",
            isSslEnabled = true
        )
    }
}