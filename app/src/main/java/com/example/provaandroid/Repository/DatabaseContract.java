package com.example.provaandroid.Repository;

import android.provider.BaseColumns;

public final class DatabaseContract {

    private DatabaseContract() {
    }

    public static class ItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "itens";
        public static final String COLUMN_TEXT = "texto";
        public static final String COLUMN_OPTION = "opcao";
    }
}
