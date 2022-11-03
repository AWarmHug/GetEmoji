import com.google.gson.Gson
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

fun main(args: Array<String>) {
    println("Hello World!")
    val emojisList = mutableListOf<Emojis>()
    val doc: Document = Jsoup.connect("https://funletu.com/emoji/").get()
    val body = doc.body()

    val sections = body.getElementsByTag("section")
    sections.forEach {
        val key = it.classNames().last()

        val name = it.getElementsByTag("h2").get(0).text()

        val emojiList = mutableListOf<Emoji>()

        val emoji_items = it.getElementsByClass("emoji_item")
        emoji_items.forEach {
            it.getElementsByClass("emoji_item").map {
                val unicode = it.classNames().last()
                val keyword = it.attr("data-keyword")

                val emoji_font = it.getElementsByClass("emoji_font").text()
                val emoji_name = it.getElementsByClass("emoji_name").text()

                return@map Emoji(unicode, emoji_name, keyword, emoji_font)
            }.let {
                emojiList.addAll(it)
            }

        }

        val emojis = Emojis(key, name, emojiList)

        emojisList.add(emojis)

    }
    val gson = Gson()
    println(gson.toJson(emojisList))


}