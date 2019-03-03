public class Assassin {

    private String name;
    private int skillLevel;

    Assassin(String name, int skillLevel){
        this.name = name;
        this.skillLevel = skillLevel;
    }

    String getName(){
        return name;
    }

    int getSkillLevel(){
        return skillLevel;
    }

    public String toString() {
        return getName() + " (" + getSkillLevel() + ")";
    }
}
