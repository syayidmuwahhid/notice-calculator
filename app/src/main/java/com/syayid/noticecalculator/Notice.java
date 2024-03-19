package com.syayid.noticecalculator;

public class Notice {
    final String[][] data = {
            // {type, harga, progresif}
            { "A5C02R52S1", "0", "0"},
            { "AFX12U21C08", "2375300", "76500" },
            { "B5D02K29M2", "2560500", "79000" },
            { "C1M02N41L1", "1933500", "0" },
            { "C1M02N42L1", "1976300", "62500" },
//            { "F1C02N46L0", "2389500", "2332500" },
            { "F1C02N28L0", "2446500", "79000" },
            { "G2E02R21L0", "2916800", "0" },
            { "H1B02N41L0", "1976300", "62500" },
            { "H1B02N42L0", "2047500", "65000" },
            { "L1F02N36L1", "2218500", "71000" },
            { "L1F02N37L1", "2318300", "74500" },
            { "L1K02Q33L1", "2802800", "91500" },
            { "N1N02Q33L1", "4056800", "0" },
            { "N1N02Q43L1", "3729000", "124000" },
            { "NF11T11C01", "1933500", "61000" },
            { "P5E02R48M1", "4284800", "0" },
            { "P5E02R49M1", "0", "0" },
            { "R5F04R24", "0", "0" },
            { "R5F04R25", "7106300", "0" },
//            { "T5C02R37L0", "3201800", "3116300" },
            { "T4G02T31L0", "4156500", "0" },
            { "T5C02R37LO", "3258800", "0" },
            { "V1J02Q32L1", "3743300", "124500" },
            { "V1J02Q50L1", "3643500", "121000" },
            { "X1H02N32M1", "2546300", "82500" },
            { "Y3B02R17L0", "2916800", "0" },

    };

    final String[][] biayaProses = {
            { "Kabupaten Sukabumi" , "1300000" },
            { "Kota Sukabumi", "1340000"}
    };

    int getNotice (String type) {
        for ( int i = 0; i < data.length; i++ ){
            if ( data[i][0].equalsIgnoreCase(type) ) {
                return Integer.valueOf(data[i][1]);
            }
        }
        return 0;
    }

//    int getTurun (String type) {
//        for ( int i = 0; i < data.length; i++ ){
//            if ( data[i][0].equalsIgnoreCase(type) ) {
//                return Integer.valueOf(data[i][2]);
//            }
//        }
//        return 0;
//    }

    int getProgresif (String type) {
        for ( int i = 0; i < data.length; i++ ){
            if ( data[i][0].equalsIgnoreCase(type) ) {
                return Integer.valueOf(data[i][2]);
            }
        }
        return 0;
    }

    int getBiayaProses (String lokasi) {
        for ( int i = 0; i < biayaProses.length; i++) {
            if (lokasi.equalsIgnoreCase(biayaProses[i][0])) {
                return Integer.valueOf(biayaProses[i][1]);
            }
        }
        return 0;
    }
}
