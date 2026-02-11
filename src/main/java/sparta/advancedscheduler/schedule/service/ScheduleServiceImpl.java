package sparta.advancedscheduler.schedule.service;

import sparta.advancedscheduler.schedule.entity.Schedule;

public interface ScheduleServiceImpl {

    Schedule findScheduleById(Long scheduleId);
}
