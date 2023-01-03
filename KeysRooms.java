/* There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0... Your goal is to visit all the rooms... However, you cannot enter a locked room without having its key... When you visit a room, you may find a set of distinct keys in it... Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms... Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise...
 * Eg 1: Rooms = [[1], [2], [3], [4]]               Output = True      Reason : Unlock Room0 pick key of 1, go to room1 pick key 2 and so on...
 * Eg 2: Rooms = [[1, 3], [3, 0, 1], [2], [0]]      Output = False     Reason : Unlock Room0 pick key 1, 3 go to room 1, 3 pick all keys, but we cannot go to room2 because room2 itself has its key...
 * Eg 3: Rooms = [[1, 2, 3], [1], [2]]              Output = True      Reason : All rooms will be visited and room key 3 will be unused since room 3 does not exist...
 * Eg 4: Rooms = [[1, 2], [0], [4], [2, 0], [3]]    Output = True
 * Eg 5: Rooms = [[], [1], [3]]                     Output = False
 */
import java.util.*;
public class KeysRooms
{   // Since passing primitive objects into generic types we will use Boolean as wrapper class, instead of boolean...
    public List<Boolean> RoomsVisited(List<List<Integer>> room)
    {   // Creating a list of rooms visited...
        List<Boolean> Visited = new ArrayList<Boolean>();
        for(int i = 0; i < room.size(); i++)
            Visited.add(false);   // Assuming all rooms unvisited in beginning...
        System.out.println("The Rooms Visited Array !!");
        System.out.print("[");
        for(int i = 0; i < room.size(); i++)
            System.out.print(Visited.get(i)+", ");
        System.out.print("]");
        System.out.println();
        return Visited;
    }
    public boolean AllRoomsVisited(List<Boolean> visited)
    {
        for(int i = 0; i < visited.size(); i++)
        {   // Checking if all rooms visited...
            if(Boolean.TRUE.equals(visited.get(i)) == false)
                return false;     // If any room unvisited return false...
        }
        return true;    // else return true...
    }
    public boolean PossibleVisit(List<List<Integer>> room)
    {
        List<Boolean> bool = new ArrayList<Boolean>();
        bool = RoomsVisited(room);
        bool.set(0, true);   // Since we have key of first room...
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 0; i < room.get(0).size(); i++)
            queue.add(room.get(0).get(i));   // Getting the key...
        System.out.println("The Queue of Room Keys formed is : ");
        while(!queue.isEmpty())   // In first iteration we have keys in the first room...
        {
            int index = queue.peek();
            if(index < bool.size())    // If the room exists...
            {
                for(int i = 0; i < room.get(index).size(); i++)
                {   // We check in the room and get the keys...
                    if(bool.get(index) == false)
                    {   // If a new room key is found add it into queue...
                        queue.add(room.get(index).get(i));
                    }
                }
            }
            else    // If room does not exist...
                index = 0;
            bool.set(index, true);
            System.out.print(queue.peek()+", ");
            queue.remove();
        }
        System.out.println();
        boolean checked = AllRoomsVisited(bool);    // Function call...
        return checked;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int x, a, k;
        System.out.print("Enter the number of rooms : ");
        x = sc.nextInt();
        List<List<Integer>> rooms = new ArrayList<List<Integer>>();
        for (int i = 0; i < x; i++)    // Setting values in 2d list...
        {
            System.out.print("Enter number of keys in "+(i+1)+" room : ");
            a = sc.nextInt();
            List<Integer> arrRow = new ArrayList<Integer>();
            for (int j = 0; j < a; j++)
            {
                System.out.print("Enter key : ");
                k = sc.nextInt();
                arrRow.add(k);
            }
            rooms.add(arrRow);
        }
        System.out.print("[");
        for(int i = 0; i < rooms.size(); i++)    // Getting values in 2d list...
        {
            System.out.print("[");
            for (int j = 0; j < rooms.get(i).size(); j++)
                System.out.print(rooms.get(i).get(j) + " ");
            System.out.print("],");
        }
        System.out.println("]");
        KeysRooms keyroom = new KeysRooms();    // object creation...
        System.out.println("Possibility if all rooms can be visited : "+keyroom.PossibleVisit(rooms));
        sc.close();
    }
}

// Time Complexity  - O(n^n) time...
// Space Complexity - O(n) time...

/* DEDUCTIONS :-
 * 1. Any room number key can be present even if the room does not exist...
 * 2. The room keys must be traversed in a queue and iterative way and Stack will not work since it will keep on popping the last element...
 * 3. The limiting factor is the keys and not the rooms...
 */