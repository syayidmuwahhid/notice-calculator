package com.syayid.noticecalculator;

public class Notice {
    final String[][] data = {
            { "A5C02R52S1", "3501000", "3401300", "112500" },
            { "AFX12U21C08", "2332500", "2275500", "73000" },
            { "B5D02K29M2", "2517800", "2446500", "79000" },
            { "C1M02N41L1", "1905000", "1848000", "58000" },
            { "C1M02N42L1", "1947800", "1890800", "59500" },
            { "F1C02N46L0", "2389500", "2332500", "75000" },
            { "G2E02R21L0", "0", "2788500", "91000" },
            { "H1B02N41L0", "1947800", "1890800", "59500" },
            { "H1B02N42L0", "2019000", "1962000", "62000" },
            { "L1F02N36L1", "2175800", "2118800", "67500" },
            { "L1F02N37L1", "2275500", "2218500", "71000" },
            { "L1K02Q33L1", "2760000", "2674500", "87000" },
            { "N1N02Q43L1", "3657800", "3558000", "118000" },
            { "NF11T11C01", "1905000", "1848000", "58000" },
            { "P5E02R48M1", "4199300", "4085300", "0" },
            { "P5E02R49M1", "4142000", "4113800", "137500" },
            { "R5F04R25", "6978000", "6778500", "0" },
            { "T5C02R37L0", "3201800", "3116300", "0" },
            { "T4G02T31L0", "4085300", "3971300", "132500" },
            { "V1J02Q32L1", "3672000", "3572300", "118500" },
            { "V1J02Q50L1", "3558000", "3472500", "115000" },
            { "X1H02N32M1", "2503500", "2432300", "78500" },
            { "Y3B02R17L0", "2859800", "2788500", "0" },

    };

    final String[][] biayaProses = {
            { "Kabupaten Sukabumi" , "1300000" },
            { "Kota Sukabumi", "1340000"}
    };

    int getNotice2023 (String type) {
        for ( int i = 0; i < data.length; i++ ){
            if ( data[i][0].equalsIgnoreCase(type) ) {
                return Integer.valueOf(data[i][1]);
            }
        }
        return 0;
    }

    int getTurun (String type) {
        for ( int i = 0; i < data.length; i++ ){
            if ( data[i][0].equalsIgnoreCase(type) ) {
                return Integer.valueOf(data[i][2]);
            }
        }
        return 0;
    }

    int getProgresif (String type) {
        for ( int i = 0; i < data.length; i++ ){
            if ( data[i][0].equalsIgnoreCase(type) ) {
                return Integer.valueOf(data[i][3]);
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
