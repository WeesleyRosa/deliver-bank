package com.deliver.bank.bank.person.entities.enums;

public enum PersonProfile {
    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private static final String INVALID_PROFILE_CODE = "Código Inválido: ";

    private Integer profileCode;
    private String profileDescription;

    PersonProfile(Integer profileCode, String profileDescription) {
        this.profileCode = profileCode;
        this.profileDescription = profileDescription;
    }

    public Integer getProfileCode() {
        return this.profileCode;
    }

    public String getProfileDescription() {
        return this.profileDescription;
    }

    public static PersonProfile toEnum(Integer profileCode) {
        if (profileCode == null) return null;

        for (PersonProfile profile : PersonProfile.values())
            if (profileCode.equals(profile.getProfileCode())) return profile;

        throw new IllegalArgumentException(PersonProfile.INVALID_PROFILE_CODE + profileCode);
    }
}
