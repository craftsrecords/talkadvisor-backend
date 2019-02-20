package org.craftsrecords.talkadvisor.recommendation.spi;

import org.craftsrecords.talkadvisor.recommendation.profile.Profile;
import org.jetbrains.annotations.NotNull;

public interface Profiles {
    void save(Profile profile);

    @NotNull
    Profile fetch(@NotNull String userId);
}
