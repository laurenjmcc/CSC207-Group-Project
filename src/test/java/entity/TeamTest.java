package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    private Team team;
    private Set<String> initialMembers;
    private String teamName;

    @BeforeEach
    void setUp() {
        teamName = "teamName";
        initialMembers = new HashSet<>(Arrays.asList("user1", "user2", "user3"));
        team = new Team(teamName, initialMembers);
    }

    @Test
    void testGetTeamName() {
        assertEquals(teamName, team.getTeamName());
    }

    @Test
    void testGetMemberUsernames() {
        Set<String> members = team.getMemberUsernames();
        assertNotNull(members);
        assertEquals(initialMembers, members);
    }

    @Test
    void testAddMember() {
        String newMember = "newMember";
        team.addMember(newMember);
        assertTrue(team.getMemberUsernames().contains(newMember));
        assertEquals(initialMembers.size() + 1, team.getMemberUsernames().size());
    }
}
