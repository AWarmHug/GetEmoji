data class Emojis(
    val key:String,
    val name:String,
    val list: List<Emoji>
)

data class Emoji(
    val unicode:String,
    val emoji_name:String,
    val keyword:String,
    val emoji_font:String
)
