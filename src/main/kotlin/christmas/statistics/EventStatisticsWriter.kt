package christmas.statistics

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

class EventStatisticsWriter {
    private val filePath = Paths.get("docs/DecemberEventStatistics.txt")
    private val fileReader: EventStatisticsReader

    init {
        if (!Files.exists(filePath)) {
            Files.createFile(filePath)
        }
        fileReader = EventStatisticsReader()
    }

    fun writeSalesAmount(salesAmount: Int) {
        val previousSales = fileReader.readPreviousSalesAmount()
        val totalSales = previousSales + salesAmount
        val salesData = "2023년 12월 판매 금액: ${String.format("%,d", totalSales)}원\n"
        Files.write(filePath, salesData.toByteArray(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
    }

    fun writeNumberOfParticipants() {
        val previousParticipants = fileReader.readPreviousParticipants()
        val numberOfParticipants = 1
        val totalParticipants = previousParticipants + numberOfParticipants
        val participantData = "2023년 12월 이벤트 참여 고객 수: ${String.format("%,d", totalParticipants)}\n"
        Files.write(filePath, participantData.toByteArray(), StandardOpenOption.CREATE, StandardOpenOption.APPEND)
    }
}
