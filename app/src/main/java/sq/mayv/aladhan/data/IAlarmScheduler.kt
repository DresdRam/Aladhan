package sq.mayv.aladhan.data

import sq.mayv.aladhan.model.Alarm

interface IAlarmScheduler {

    fun schedule(alarm: Alarm)

    fun scheduleMultiple(alarms: List<Alarm>)

}