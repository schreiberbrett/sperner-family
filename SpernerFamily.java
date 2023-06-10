import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class SpernerFamily<T> {
    private List<Set<T>> sets;

    public SpernerFamily() {
        this.sets = new ArrayList<Set<T>>();
    }

    /**
     * Add the set to the Sperner family and remove any supersets.
     * 
     * Runs in O(n^2 log n) time for n elements in the set, and n sets in the family, assuming that checking membership in a set takes O(log n) time.
     * 
     * @return true if the set was successfully added, false if it itself was a superset of one of the sets already in the Sperner family.
     */
    public boolean add(Set<T> setToAdd) {
        var nonSupersets = new ArrayList<Set<T>>();

        for (var set : sets) {
            if (isSubset(set, setToAdd)) {
                return false;
            }

            if (!isSubset(setToAdd, set)) {
                nonSupersets.add(set);
            }
        }

        this.sets = nonSupersets;
        this.sets.add(setToAdd);

        return true;
    }

    public int size() {
        return this.sets.size();
    }

    public List<Set<T>> getSets() {
        return Collections.unmodifiableList(this.sets);
    }

    private static <S> boolean isSubset(Set<S> subset, Set<S> superset) {
        for (var element : subset) {
            if (!superset.contains(element)) {
                return false;
            }
        }

        return true;
    }
}