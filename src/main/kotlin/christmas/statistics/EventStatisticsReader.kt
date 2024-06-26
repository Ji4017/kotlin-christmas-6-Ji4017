package christmas.statistics

import java.nio.file.Files
import java.nio.file.Paths

class EventStatisticsReader {
    private val filePath = Paths.get("docs/DecemberEventStatistics.txt")
    private val lines: List<String>

    init {
        lines = Files.readAllLines(filePath)
    }

    fun readPreviousSalesAmount(): Int {
        val salesPattern = Regex("판매 금액: ([0-9,]+)원")

        for (line in lines) {
            if (line.contains(salesPattern)) {
                val matchResult = salesPattern.find(line)
                if (matchResult != null) {
                    val salesString = matchResult.groupValues[1].replace(",", "")
                    return Integer.parseInt(salesString)
                }
            }
        }
        return 0
    }

    fun readPreviousParticipants(): Int {
        val participantPattern = Regex("참여 고객 수: ([0-9,]+)")

        for (line in lines) {
            if (line.contains(participantPattern)) {
                val matchResult = participantPattern.find(line)
                if (matchResult != null) {
                    val participantString = matchResult.groupValues[1].replace(",", "")
                    return Integer.parseInt(participantString)
                }
            }
        }
        return 0
    }
}
