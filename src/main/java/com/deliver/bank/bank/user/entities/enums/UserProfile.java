package com.deliver.bank.bank.user.entities.enums;

public enum UserProfile {
    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private static final String INVALID_PROFILE_CODE = "Código Inválido: ";

    private Integer profileCode;
    private String profileDescription;

    UserProfile(Integer profileCode, String profileDescription) {
        this.profileCode = profileCode;
        this.profileDescription = profileDescription;
    }

    public Integer getProfileCode() {
        return this.profileCode;
    }

    public String getProfileDescription() {
        return this.profileDescription;
    }

    public static UserProfile toEnum(Integer profileCode) {
        if (profileCode == null) return null;

        for (UserProfile profile : UserProfile.values())
            if (profileCode.equals(profile.getProfileCode())) return profile;

        throw new IllegalArgumentException(UserProfile.INVALID_PROFILE_CODE + profileCode);
    }
}
