package com.cubecrusher.warthgame.maps;

import com.badlogic.gdx.graphics.Color;

public class Belarus {
    private float[] vitebskO, grodnoO, minskO, mogilevO, brestO, gomelO;


    public Belarus(){
    }

    public int getRegionAmt(){
        return 6;
    }

    public com.badlogic.gdx.graphics.Color getRegionColor(int id){
        switch (id){
            case 0:
                return com.badlogic.gdx.graphics.Color.WHITE;
            case 1:
                return com.badlogic.gdx.graphics.Color.BLUE;
            default:
                return Color.GRAY;
        }
    }

    public float[] getVertices(int id){
        switch (id){
            case 0:
                return vitebskShape();
            case 1:
                return brestShape();
            default:
                return new float[]{0.0f, 0.0f, 100f, 100f, 100f, 0f};
        }
    }

    public String getRegionName(int id){
        switch (id){
            case 0:
                return "Vitebsk Oblast";
            case 1:
                return "Grodno Oblast";
            case 2:
                return "Minsk Oblast";
            case 3:
                return "Mogilev Oblast";
            case 4:
                return "Brest Oblast";
            case 5:
                return "Gomel Oblast";
            default:
                return "Unknown";
        }
    }

    private float[] vitebskShape(){
        return new float[]{0, 0,
                22, 3,
                46, 2,
                47, 13,
                56, 10,
                52, 1,
                57, -5,
                78, -7,
                83, 3,
                105, -1,
                118, -9,
                131, -1,
                140, 12,
                164, 8,
                177, -9,
                190, -15,
                190, -20,
                207, -24,
                204, -8,
                212, -8,
                220, -18,
                228, -15,
                229, -24,
                227, -31,
                228, -39,
                219, -51,
                230, -56,
                226, -62,
                241, -62,
                251, -69,
                258, -59,
                273, -59,
                293, -71,
                296, -95,
                319, -95,
                331, -105,
                355, -99,
                355, -76,
                346, -69,
                371, -56,
                382, -67,
                395, -64,
                393, -87,
                379, -109,
                354, -109,
                350, -117,
                365, -136,
                363, -155,
                388, -186,
                384, -254,
                408, -293,
                441, -296,
                452, -309,
                469, -311,
                478, -295,
                492, -282,
                488, -263,
                505, -263,
                516, -242,
                525, -240,
                523, -226,
                532, -209,
                546, -207,
                546, -177,
                536, -182,
                543, -160,
                535, -137,
                524, -136,
                514, -107,
                497, -100,
                493, -80,
                465, -86,
                449, -72,
                438, -78,
                429, -60,
                439, -54,
                456, -61,
                453, -46,
                460, -26,
                485, -35,
                482, -11,
                497, -19,
                506, -5,
                490, 4,
                496, 12,
                470, 21,
                469, 38,
                477, 39,
                475, 51,
                485, 69,
                495, 70,
                522, 109,
                531, 153,
                493, 166,
                436, 164,
                413, 153,
                392, 158,
                383, 154,
                369, 186,
                362, 188,
                356, 216,
                362, 222,
                362, 237,
                356, 240,
                362, 247,
                359, 256,
                334, 270,
                331, 286,
                312, 287,
                314, 306,
                321, 307,
                315, 316,
                305, 303,
                295, 307,
                295, 315,
                287, 308,
                270, 317,
                271, 291,
                263, 301,
                258, 288,
                213, 284,
                212, 272,
                191, 287,
                200, 295,
                187, 306,
                192, 314,
                186, 336,
                157, 343,
                156, 334,
                116, 338,
                117, 326,
                102, 330,
                75, 333,
                72, 288,
                76, 285,
                73, 260,
                64, 252,
                73, 225,
                64, 206,
                51, 199,
                53, 186,
                15, 78,
                17, 59,
                8, 45,
                11, 27,
                4, 23};
        // 147 coordinates, my friends. Every single one added by hand. That is one region.
        // Yet this was faster than the amount of time I need to figure out JSON parsing.
    }

    private float[] brestShape(){
        return new float[]{75, 333,
                75, 352,
                41, 380,
                23, 378,
                -5, 395,
                -34, 438,
                -56, 470,
                -51, 472,
                -53, 486,
                -41, 491,
                -24, 491,
                -16, 501,
                0, 500,
                -3, 507,
                18, 518,
                18, 525,
                26, 528,
                27, 547,
                32, 553,
                22, 563,
                19, 575,
                23, 579,
                21, 586,
                18, 586,
                17, 597,
                23, 605,
                13, 618,
                9, 617,
                3, 625,
                7, 628,
                6, 662,
                13, 671,
                9, 676,
                21, 687,
                30, 687,
                22, 674,
                28, 667,
                18, 654,
                48, 642,
                50, 650,
                71, 651,
                64, 659,
                65, 664,
                85, 667,
                90, 659,
                98, 657,
                105, 644,
                141, 621,
                136, 604,
                145, 593,
                144, 589,
                151, 583,
                177, 582,
                211, 580,
                214, 583,
                226, 574,
                231, 579,
                243, 574,
                245, 579,
                252, 579,
                272, 565,
                281, 564,
                287, 558,
                304, 559,
                329, 573,
                345, 572,
                348, 566,
                391, 572,
                389, 564,
                400, 565,
                399, 570,
                413, 574,
                437, 570,
                437, 573,
                445, 574,
                456, 588,
                491, 589,
                496, 583,
                496, 596,
                502, 595,
                522, 606,
                529, 598,
                560, 606,
                558, 613,
                576, 616,
                591, 625,
                599, 614,
                636, 613,
                637, 643,
                644, 642,
                648, 648,
                642, 660,
                662, 656,
                674, 660,
                684, 658,
                682, 663,
                687, 665,
                690, 649,
                684, 643,
                682, 631,
                686, 625,
                693, 624,
                692, 613,
                697, 603,
                686, 590,
                693, 582,
                689, 564,
                705, 551,
                705, 542,
                699, 542,
                698, 534,
                691, 534,
                695, 504,
                691, 498,
                697, 492,
                692, 477,
                670, 470,
                665, 461,
                645, 461,
                648, 441,
                640, 440,
                641, 432,
                628, 433,
                629, 425,
                621, 423,
                622, 413,
                610, 413,
                606, 386,
                599, 385,
                600, 376,
                606, 377,
                602, 364,
                612, 359,
                610, 350,
                604, 350,
                608, 342,
                604, 332,
                594, 345,
                579, 338,
                579, 332,
                565, 335,
                559, 311,
                537, 310,
                530, 299,
                520, 318,
                517, 309,
                504, 320,
                495, 315,
                494, 308,
                507, 294,
                496, 272,
                508, 269,
                508, 254,
                519, 257,
                517, 243,
                511, 243,
                511, 235,
                499, 235,
                486, 218,
                477, 215,
                476, 209,
                483, 209,
                482, 201,
                488, 201,
                488, 195,
                496, 195,
                499, 178,
                493, 166,
                436, 164,
                413, 153,
                392, 158,
                383, 154,
                369, 186,
                362, 188,
                356, 216,
                362, 222,
                362, 237,
                356, 240,
                362, 247,
                359, 256,
                334, 270,
                331, 286,
                312, 287,
                314, 306,
                321, 307,
                315, 316,
                305, 303,
                295, 307,
                295, 315,
                287, 308,
                270, 317,
                271, 291,
                263, 301,
                258, 288,
                213, 284,
                212, 272,
                191, 287,
                200, 295,
                187, 306,
                192, 314,
                186, 336,
                157, 343,
                156, 334,
                116, 338,
                117, 326,
                102, 330};
        // 206 coordinates.
    }

    private float[] minskShape(){
        return new float[]{

        };
    }
}
