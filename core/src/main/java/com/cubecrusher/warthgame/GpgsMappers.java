package com.cubecrusher.warthgame;

public class GpgsMappers {
    public static String mapToGpgsLeaderboard(String leaderboardId) {
        String gpgsId = null;

        if (leaderboardId != null) {
            if (leaderboardId.equals(Main.LEADERBOARD1))
                gpgsId = "CgkIu46Sr-8fEAIQAw";
        }

        return gpgsId;
    }

    public static String mapToGpgsAchievement(String achievementId) {
        String gpgsId = null;

        if (achievementId != null) {
            if (achievementId.equals(Main.ACHIEVEMENT1))
                gpgsId = "CgkIgszi1rUYEAIQAQ";
        }

        return gpgsId;
    }

    public static String mapToGpgsEvent(String eventId) {
        String gpgsId = null;

        if (eventId != null) {
            if (eventId.equals(Main.EVENT1))
                gpgsId = "CgkIu46Sr-8fEAIQAQ";
        }

        return gpgsId;
    }
}