package entity;

import java.util.HashSet;
import java.util.Set;

public class Team {
    private final String teamName;
    private final Set<String> memberUsernames;


    public Team(String teamname, Set<String> memberUsernames) {
        this.teamName = teamname;
        this.memberUsernames = new HashSet<>(memberUsernames);
    }

    public String getTeamName() {
        return teamName;
    }

    public Set<String> getMemberUsernames() {
        return memberUsernames;
    }

    public void addMember(String username) {
        memberUsernames.add(username);
    }
}
