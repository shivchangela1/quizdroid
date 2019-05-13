package edu.washington.shivc.quizdroid

class OtherRepository: TopicRepository {
    override var topics: Array<TopicObj> = makeObjects()

    private fun makeObjects(): Array<TopicObj> {
        val topicList = mutableListOf<TopicObj>()
        val subjects = arrayOf("Math", "Physics", "Marvel Superheroes")
        for (i in 0..2) {
            val questionList = mutableListOf<QuizObj>()
            for (i in 0..3) {
                val question = QuizObj("This is a question", arrayOf("1", "2", "3", "4"), 1)
                questionList.add(question)
            }
            val topic = TopicObj(subjects[i], "This is a short description",
                    "This is a long description", questionList.toTypedArray())
            topicList.add(topic)
        }
        return (topicList.toTypedArray())
    }

    override fun getTopic(index: Int): TopicObj {
        return (topics[index])
    }
}