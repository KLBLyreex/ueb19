public class Test {

    public static void main(String[] args){
        DoppeltVKListe vkl = new DoppeltVKListe();
        Person p = new Person("Manfred", "Manfred");
        Person p2 = new Person("Otto", "Manfred");
        Person p3 = new Person("Manfred", "Otto");
        vkl.add(p);
        vkl.add(p2);
        for(int i = 0; i < vkl.size(); i++){
            System.out.println(vkl.get(i));
        }
        System.out.println("###");
        vkl.add(0, p3);

        for(int i = 0; i < vkl.size(); i++){
            System.out.println(vkl.get(i));
        }

    }
}
