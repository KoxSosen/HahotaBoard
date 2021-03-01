package me.hahota.simon.scoreboard;

import com.moandjiezana.toml.Toml;

public class configvalues {

    public class TomlConfig {

        private String Title;
        private String Score1;
        private String Score2;
        private String Score3;
        private String Score4;
        private String Score5;
        private String Vault;
        private String Papi;


    }

    public void applyToClass(Toml toml) {
        toml.to(TomlConfig.class);
    }

}
