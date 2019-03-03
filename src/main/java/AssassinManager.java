import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AssassinManager {

    private LinkedList<Assassin> killRing = new LinkedList<>();
    private Node<Assassin> victim = null;
    private Node<Assassin> coldHardKiller = null;
    private StringBuilder finalResult = new StringBuilder();
    private StringBuilder recap = new StringBuilder();
    private static final List<String> weapons = Arrays.asList(
            "plushy", "paper scraps", "basketball", "feather",
            "mirror", "child proof scissors", "unwanted lecture"
    );
    private static final List<String> places = Arrays.asList(
            "Police Station", "Bank", "Coffee Shop getting an Americano",
            "store of Bath & Body Works", "Panic Room", "company of the President",
            "Self Defense Classroom", "beaches of an deserted island"
    );

    AssassinManager(String filePath) throws IOException{
        Scanner playerInfo = new Scanner(new File(filePath)).useDelimiter(", | |\r\n");
        int skill;
        String name;

        while(playerInfo.hasNext()){
            String playerData = playerInfo.next();
            name = playerData;
            playerData = playerInfo.next();
            skill = Integer.parseInt(playerData);

            Assassin player = new Assassin(name, skill);
            Node<Assassin> character = new Node<>(player);
            killRing.add(character);
        }
        play(killRing);
    }

    Node<Assassin> getNextVictim(LinkedList<Assassin> killRing) {
        Node<Assassin> firstAssassin = killRing.first;
        Node<Assassin> currentAssassin = killRing.first;
        Node<Assassin> previousAssassin = null;
        Random rng = new Random();

        int highestSkill = 0;
        int currentSkill;
        int skillOfPotentialKiller = currentAssassin.data.getSkillLevel();
        int skillOfPotentialVictim;
        int n;

        while(currentAssassin.next != null){
            previousAssassin = currentAssassin;
            currentAssassin = currentAssassin.next;
            skillOfPotentialVictim = currentAssassin.data.getSkillLevel();
            currentSkill = skillOfPotentialKiller - skillOfPotentialVictim;

            if(skillOfPotentialVictim == skillOfPotentialKiller){
                n = rng.nextInt(100 ) + 1;
                if(n <= 50){
                    victim = currentAssassin;
                    coldHardKiller = killRing.getParent(victim);
                }else{
                    victim = previousAssassin;
                    coldHardKiller = killRing.getParent(victim);
                }
            }else if(currentSkill > highestSkill){
                highestSkill = currentSkill;
                victim = currentAssassin;
                coldHardKiller = killRing.getParent(victim);
            }
            skillOfPotentialKiller = skillOfPotentialVictim;
        }

        skillOfPotentialVictim = previousAssassin.data.getSkillLevel();
        if(skillOfPotentialVictim == skillOfPotentialKiller) {
            n = rng.nextInt(100 ) + 1;
            if (n <= 50) {
                victim = currentAssassin;
                if(killRing.size() > 1){
                    coldHardKiller = killRing.getParent(victim);
                }else{
                    coldHardKiller = firstAssassin;
                }
            }else{
                if(killRing.size() > 1){
                    victim = firstAssassin;
                    coldHardKiller = currentAssassin;
                }else {
                    victim = firstAssassin;
                    coldHardKiller = currentAssassin;
                }
            }
        }

        skillOfPotentialKiller = currentAssassin.data.getSkillLevel();
        skillOfPotentialVictim = firstAssassin.data.getSkillLevel();
        currentSkill = skillOfPotentialKiller - skillOfPotentialVictim;

        if(currentSkill > highestSkill){
            victim = firstAssassin;
            coldHardKiller = currentAssassin;
        }
        deathRecap();
        return victim;
    }

    void play(LinkedList<Assassin> killRing){
        System.out.println(printKillRing());
        while(killRing.size() > 1){
            Node<Assassin> victim = getNextVictim(killRing);
            killRing.remove(victim);
        }
        System.out.println(deathRecap());
    }

    String printKillRing(){
        Node<Assassin> current = killRing.first;
        StringBuilder result = new StringBuilder("Kill Ring: ");
        while(current != null){
            result.append(current.data).append(" > ");
            current = current.next;
        }
        result.append(killRing.first.data);
        return result + "";
    }

    String deathRecap(){
        String weapon = weapons.get(new Random().nextInt(weapons.size()));
        String place = places.get(new Random().nextInt(places.size()));
        if(killRing.size() > 1){
            finalResult.append(coldHardKiller.data.getName());
            finalResult.append(" killed ").append(victim.data.getName()).append(" in the ");
            finalResult.append(place).append(" with the ").append(weapon).append("!\n");
            recap.append(coldHardKiller.data.getName()).append(" killed ");
            recap.append(victim.data.getName()).append(", ");
        }else{
            recap.delete(recap.length() - 2, recap.length());
            finalResult.append("The winner is ").append(coldHardKiller.data.getName()).append("!");
            finalResult.append("\n").append(recap).append(".");
        }
        return finalResult + "\n";
    }

}
