package com.example.quickread.data

import com.example.quickread.models.Book


object BookRepository {

    private val _books = mutableListOf(

        createBook(
            id = "1",
            title = "Mystery of the Forest",
            author = "Ava Woods",
            genre = "Mystery",
            coverImage = "cover1"
        ),
        createBook(
            id = "2",
            title = "Love in Paris",
            author = "Claire Belle",
            genre = "Romantic",
            coverImage = "cover2"
        ),
        createBook(
            id = "3",
            title = "Alien Attack",
            author = "Zane Fox",
            genre = "Sci-Fi",
            coverImage = "cover3"
        ),
        createBook(
            id = "4",
            title = "The Comic Hero",
            author = "Leo Jester",
            genre = "Comedy",
            coverImage = "cover4"
        ),
        createBook(
            id = "5",
            title = "Samurai Spirit",
            author = "Kenji Yamato",
            genre = "Action",
            coverImage = "cover5"
        ),
        createBook(
            id = "6",
            title = "Mindful Living",
            author = "Sophie Zen",
            genre = "Non-Fiction",
            coverImage = "cover6"
        )
    )


    private val pageBookmarks = mutableMapOf<String, Int>()

    fun bookmarkPage(bookId: String, page: Int) {
        pageBookmarks[bookId] = page
    }

    fun getBookmarkedPage(bookId: String): Int? = pageBookmarks[bookId]

    fun getBooks(): List<Book> = _books

    fun getBookById(id: String): Book? = _books.find { it.id == id }

    fun getBooksByGenre(genre: String): List<Book> =
        _books.filter { it.genre.equals(genre, ignoreCase = true) }

    fun getRecommendedBooks(): List<Book> = _books.shuffled().take(5)

    fun toggleFavorite(bookId: String) {
        _books.find { it.id == bookId }?.let {
            val updated = it.copy(isFavorite = !it.isFavorite)
            val index = _books.indexOf(it)
            _books[index] = updated
        }
    }

    fun toggleBookmark(bookId: String) {
        _books.find { it.id == bookId }?.let {
            val updated = it.copy(bookmarked = !it.bookmarked)
            val index = _books.indexOf(it)
            _books[index] = updated
        }
    }

    fun updateProgress(bookId: String, page: Int) {
        _books.find { it.id == bookId }?.let {
            val updated = it.copy(progress = page, lastPageRead = page)
            val index = _books.indexOf(it)
            _books[index] = updated
        }
    }

    fun getFavoriteBooks(): List<Book> = _books.filter { it.isFavorite }

    fun getBookmarkedBooks(): List<Book> = _books.filter { it.bookmarked }

    fun addToCurrentlyReading(book: Book) {
        val index = _books.indexOfFirst { it.id == book.id }
        if (index != -1) {
            _books[index] = _books[index].copy(status = "Currently Reading")
        }
    }

    private fun createBook(id: String, title: String, author: String, genre: String, coverImage: String): Book {
        val story = when (genre) {
            "Mystery" -> listOf(
                "Page 1: The forest loomed ahead, silent and still. Elara tightened her coat as she stepped past the warning sign that read: 'Enter at your own risk.'",
                "Page 2: She followed the overgrown path, crunching leaves underfoot, until she stumbled upon a rusted lantern still warm to the touch.",
                "Page 3: A sudden rustle behind her made her spin—nothing. Just trees. But then she noticed the symbols carved into the bark: spirals, stars, eyes.",
                "Page 4: Elara scribbled them in her notebook. They matched those found at the old mill murder scene. This was no coincidence.",
                "Page 5: The deeper she walked, the darker it got. Her phone lost signal. She lit the lantern. It hissed and flickered to life.",
                "Page 6: In the lantern's glow, she saw a path of red string tied between branches. Someone had marked a trail.",
                "Page 7: She followed it until she reached a clearing. In the center: an old well. The rope was frayed, but a bucket sat beside it, wet.",
                "Page 8: Elara heard whispers from the trees. She held her breath. A figure stepped out, cloaked and hooded. 'You shouldn't be here,' it said.",
                "Page 9: Before she could speak, the figure vanished into the woods. She chased, stumbling through roots, branches scratching her skin.",
                "Page 10: She tripped and fell—onto something soft. A tattered journal. Inside, entries dated 1978: 'They took my brother. The forest hides them.'",
                "Page 11: She read on. The last entry warned: 'The forest feeds on secrets. Speak none, or it learns.'",
                "Page 12: Elara stuffed the journal into her bag and turned back toward the well. It was gone. Only trees remained where it stood.",
                "Page 13: A low growl echoed. She ran, but the path was gone. The forest had shifted. The red string now pointed behind her.",
                "Page 14: She turned and followed it again, panting, until a wooden cabin came into view. The door was ajar, creaking.",
                "Page 15: Inside, dusty furniture, broken glass, and another lantern. A mirror hung crooked on the wall, reflecting someone behind her.",
                "Page 16: She whirled around—no one. But the mirror showed her standing with a child. A boy. The same one from the journal?",
                "Page 17: Her lantern flickered. The child in the mirror whispered: 'Help me.' Then his hand pressed to the glass from the inside.",
                "Page 18: The door slammed. Cold air blew. The mirror cracked. The boy’s face faded. A new carving appeared on the wall: Elara’s name.",
                "Page 19: Her hands trembled. She dropped the lantern and ran, screaming. Trees opened, the path returned. Light broke through.",
                "Page 20: She collapsed outside the forest edge, gasping. Behind her, the forest stood still. But now, carved into the sign: 'One more secret kept.'"
            )

            "Romantic" -> listOf(
                "Page 1: Elise adjusted her scarf and stepped into Café Lumière. Rain danced on the cobblestones outside, and the scent of roasted espresso filled the air.",
                "Page 2: She ordered a café au lait and settled near the window. A stranger sat across from her, uninvited but smiling. 'You took my spot,' he said in a warm voice.",
                "Page 3: Blushing, she offered to move. He chuckled, 'Only if you want me to disappear from your story.' She didn’t.",
                "Page 4: His name was Marc. A photographer. He carried a leather journal and wrote with a fountain pen. He asked about her dreams, not her job.",
                "Page 5: Over croissants, they shared childhood tales. Hers of painting, his of climbing rooftops to photograph sunrises.",
                "Page 6: They met again the next day. And the next. Soon, their lives began to orbit Café Lumière like moons to gravity.",
                "Page 7: One evening, he handed her a photo — a candid shot of her smiling at a pigeon. She laughed, then looked away. Vulnerable.",
                "Page 8: They walked along the Seine. He stopped and said, 'You feel like home.' She felt it too — terrifying and true.",
                "Page 9: But Paris is cruel with goodbyes. His visa was expiring. One week left. They made a pact: no promises, only memories.",
                "Page 10: They spent the week chasing sunsets, dancing in alleyways, and getting lost in bookstores.",
                "Page 11: On their last day, he gave her a key — to the café. 'If you ever return, you’ll always have a seat.'",
                "Page 12: He left. Just like that. No tears. No goodbye kiss. Only a lingering look across a terminal and silence.",
                "Page 13: Years passed. She returned to Paris, now a painter. The city felt the same — but she didn’t.",
                "Page 14: On impulse, she visited the café. It hadn’t changed. Neither had the chair by the window.",
                "Page 15: A new photo hung on the wall — a woman feeding pigeons. Her. The photo Marc took. Her heart stuttered.",
                "Page 16: A note was pinned beside it: 'For Elise — If you return, I’ll be waiting where we began. - M.'",
                "Page 17: She turned, and there he was. Older. Softer. But his eyes were the same. So was his smile.",
                "Page 18: He walked up to her slowly. 'Took you long enough,' he whispered. She cried and laughed at once.",
                "Page 19: They sat by the window. Outside, the rain started again. Paris welcomed her home.",
                "Page 20: She reached for his hand. It felt exactly like that first day — warm, familiar, and full of new beginnings."
            )

            "Sci-Fi" -> listOf(
                "Page 1: Earth lost contact with Mars Base Echo. At first, they blamed storms. Then the screaming began.",
                "Page 2: Captain Rylor received the emergency broadcast. Static, followed by, 'They're inside. Don't trust—' then silence.",
                "Page 3: The Aurora launched with a crew of five. Mission: rescue or recover. Nobody said 'survive'.",
                "Page 4: They landed on Mars to find red dust... and a trail of footprints. But no bodies. No blood. Just claw marks.",
                "Page 5: They entered the base. Walls scorched, furniture shattered. One word was smeared in ash: 'RUN'.",
                "Page 6: A noise. Something skittered in the vents. Then another. And another.",
                "Page 7: The medic vanished during sleep. Only her headset remained, still broadcasting heartbeats... two of them.",
                "Page 8: They found a lab. In a sealed tube: a creature — eight-limbed, pulsing with light. A note read: 'Do not wake.'",
                "Page 9: Too late. Its container was cracked. Something had escaped... or evolved.",
                "Page 10: The engineer began muttering in a foreign language — Martian glyphs appeared on his skin.",
                "Page 11: One by one, the crew began hallucinating. Seeing dead relatives. Being pulled into airlocks by invisible hands.",
                "Page 12: Rylor initiated lockdown. 'If I die, nuke the site. Don’t let it reach Earth.'",
                "Page 13: The alien was not one — it was many. A hive mind. Feeding off fear. It had learned their names.",
                "Page 14: They set the reactor to overload. One chance to burn it all.",
                "Page 15: The crew sacrificed themselves one by one to buy time. Rylor reached the escape pod — barely.",
                "Page 16: As she ascended, the base exploded. A red mushroom cloud on a dead world.",
                "Page 17: Earth hailed her a hero. But Rylor said nothing. Not about the voices she still heard.",
                "Page 18: In her mirror, her eyes flashed — not blue, but glowing white.",
                "Page 19: A final journal entry: 'I’m not sure I came back alone.'",
                "Page 20: The stars outside her ship blinked in rhythm. A pattern. A message. And it called her by name."
            )

            "Comedy" -> listOf(
                "Page 1: Meet Bob. Accountant by day, 'Captain Cringe' by night — self-proclaimed superhero with zero powers.",
                "Page 2: Bob's costume? Red tights, yellow cape, and a giant spoon duct-taped to his belt. He thought it was his 'power utensil'.",
                "Page 3: First mission: stop a purse thief. He tripped on a fire hydrant. The thief helped him up, then ran.",
                "Page 4: Undeterred, he upgraded his gear — roller skates and bubble wrap armor. He crashed into a hotdog stand.",
                "Page 5: Local kids started filming him. 'Captain Cringe Compilation Vol. 1' went viral overnight.",
                "Page 6: Bob became a meme. His motto? 'Safety second!'",
                "Page 7: He tried to rescue a cat. Ended up chased by a pack of chihuahuas instead.",
                "Page 8: The mayor called him a public hazard. Bob called himself 'misunderstood'.",
                "Page 9: One day, he accidentally stopped a robbery — by slipping on spilled milk and knocking out the thief.",
                "Page 10: Headlines read: 'Clown Saves Day!' Bob framed it.",
                "Page 11: Inspired, he opened a hotline: 1-800-CRINGE. His mom was the first caller.",
                "Page 12: He invented gadgets: glitter grenades, a confetti cannon, and a megaphone that only played polka.",
                "Page 13: Villains started ignoring him. 'It’s just Bob,' they’d say.",
                "Page 14: But the people? They loved him. Not for success — but for never giving up.",
                "Page 15: He even got a sidekick: 'Kid Kaboom' — a 12-year-old with too many ideas and too few rules.",
                "Page 16: Together they caused chaos — and a surprising amount of accidental good.",
                "Page 17: Bob tripped into danger more than he walked. But somehow, the city smiled a little more.",
                "Page 18: On his 100th mission, he stopped a giant balloon from floating into power lines... by sitting on it.",
                "Page 19: His cape? Still crooked. His spoon? Still taped. His heart? Still heroic.",
                "Page 20: Because sometimes, being a hero isn’t about powers — it’s about showing up with glitter and guts."
            )

            "Action" -> listOf(
                "Page 1: In the village of Kurohana, a blade slept beneath the temple floor. Waiting for its master.",
                "Page 2: Kenji, an orphaned boy, trained in silence. His sensei taught him: 'The sword is not for war, but for peace kept with steel.'",
                "Page 3: Raiders came. Kenji watched as flames consumed his childhood. He vowed revenge — not in anger, but in duty.",
                "Page 4: He left to find the Shattered Cliffs, where spirits whispered to the worthy.",
                "Page 5: There, he faced three trials: wind, fire, and shadow. He passed — barely.",
                "Page 6: The sword accepted him. Black steel, wrapped in dragon-hide, humming with power.",
                "Page 7: He returned to Kurohana, now a ghost town. The raiders had moved on — to the next village.",
                "Page 8: One by one, he hunted them down. Not with rage, but precision.",
                "Page 9: The last one begged for mercy. Kenji spared him. 'Live to remember,' he said.",
                "Page 10: Word spread. The Ghost Samurai, they called him.",
                "Page 11: A corrupt lord feared his rise and sent assassins. They never returned.",
                "Page 12: Kenji trained others — not to fight, but to protect.",
                "Page 13: A girl, Yuna, became his student. Fierce. Wise. She reminded him of who he was before the blade.",
                "Page 14: Together, they built a new dojo in the mountains. A place of healing.",
                "Page 15: But peace never lasts. An army marched to claim the sword for the emperor.",
                "Page 16: Kenji stood alone at the pass. Yuna by his side. They held the line.",
                "Page 17: Thunder roared. Arrows flew. The mountains shook with battle cries.",
                "Page 18: By dawn, the enemy retreated. Kenji knelt, bleeding, smiling.",
                "Page 19: His last words: 'Guard the flame, not the ashes.'",
                "Page 20: Yuna took his blade. The legend lived on — not in war, but in those who chose to protect."
            )

            "Non-Fiction" -> listOf(
                "Page 1: Mindfulness begins with noticing the breath. One inhale. One exhale. Nothing more, nothing less.",
                "Page 2: Your thoughts will wander. That’s not failure — that’s proof you’re human.",
                "Page 3: Morning rituals shape the day. Stretch, breathe, set one intention: 'Today, I will be present.'",
                "Page 4: Practice gratitude. Not for perfection — but for progress. List three small wins.",
                "Page 5: Mindful eating means tasting, not rushing. What does your food sound like when you chew slowly?",
                "Page 6: The phone isn’t evil — but your relationship with it matters. Set boundaries. Take tech breaks.",
                "Page 7: Mindfulness at work = single-tasking. Multitasking is a myth. Focus is a gift.",
                "Page 8: Feeling anxious? Try the 5-4-3-2-1 grounding exercise. It works. Every time.",
                "Page 9: Journaling isn’t about perfect grammar. It’s about honesty. Write what you can’t say.",
                "Page 10: Stillness is uncomfortable because we’re addicted to noise. Sit with it anyway.",
                "Page 11: You won’t always feel zen. Mindfulness isn’t calm — it’s awareness, even in chaos.",
                "Page 12: Breathe before you speak. Responding is power. Reacting is impulse.",
                "Page 13: Forgive yourself. Then again. And again. That’s part of the journey.",
                "Page 14: Nature heals. Even five minutes under a tree rewires your nervous system.",
                "Page 15: Replace 'I have to' with 'I get to.' Watch your mindset shift.",
                "Page 16: End your day by releasing tension. Scan your body. Let it soften.",
                "Page 17: Sleep is sacred. Make a ritual. No screens. Just stillness and breath.",
                "Page 18: Talk kindly to yourself. Your inner voice sets the tone for the world.",
                "Page 19: Mindfulness isn’t a destination. It’s a returning — over and over again.",
                "Page 20: And if all else fails... just breathe. That’s enough. You’re enough."
            )


            else -> List(20) { i -> "Page ${i + 1}: Story content not available." }
        }

        return Book(
            id = id,
            title = title,
            author = author,
            genre = genre,
            description = "$title is a captivating $genre book written by $author.",
            status = "Available",
            progress = 0,
            isFavorite = false,
            bookmarked = false,
            lastPageRead = 0,
            coverImage = coverImage,
            story = story
        )
    }
}
