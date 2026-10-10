/*
package automata;

public class DfaDemo {
}

*/
import automata.DFA;
import automata.DFAs;
import automata.Pair;

void main() {
    Map<Pair<String, Character>, String> map = Map.of(
            new Pair<>("q1", '0'), "q1",
            new Pair<>("q1", '1'), "q2",
            new Pair<>("q2", '0'), "q3",
            new Pair<>("q2", '1'), "q2",
            new Pair<>("q3", '0'), "q2",
            new Pair<>("q3", '1'), "q2"
    );
    DFA<String> dfa = new DFA<>(Set.of("q1", "q2", "q3"), Set.of('0', '1'), map::get, "q1", Set.of("q2"));
    IO.println(dfa.accepts("1101"));   // true
    IO.println(dfa.accepts("101000")); // false
    IO.println(dfa);

    DFA<String> complementDfa = DFAs.complement(dfa);
    IO.println(complementDfa.accepts("1101"));   // false
    IO.println(complementDfa.accepts("101000")); // true
    IO.println(complementDfa);
}