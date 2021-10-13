package com.excmul.follow.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.TreeSet;

@Embeddable
public class Follows {

    @OneToMany(mappedBy = "subjectMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Follow> toFollows = new TreeSet<>();

    @OneToMany(mappedBy = "targetMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Follow> fromFollows = new TreeSet<>();

    protected Follows() {
    }

    public void addToFollows(Follow follow) {
        toFollows.add(follow);
    }

    public void addFromFollows(Follow follow) {
        fromFollows.add(follow);
    }

    public void removeToFollows(Follow follow) {
        toFollows.remove(follow);
    }

    public void removeFromFollows(Follow follow) {
        fromFollows.remove(follow);
    }

    public boolean existsToFollows(Follow follow) {
        if (toFollows.contains(follow)) {
            return true;
        }
        return false;
    }

    public int countToFollows() {
        return toFollows.size();
    }

    public int countFromFollows() {
        return fromFollows.size();
    }
}
