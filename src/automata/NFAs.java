package automata;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class NFAs {
    // may be a big problem here with using null! (also in below methods)
    static <S> NFA<S> oneAcceptState(NFA<S> original) {
        if (original.acceptStates().size() == 1) {
            return original;
        } else {
            S acceptState = null;

            Set<S> states = new HashSet<>(original.states());
            states.add(acceptState);

            Function<Pair<S, Character>, Set<S>> transitionFunction = pair -> {
                S state = pair.first();
                char symbol = pair.second();

                if (original.states().contains(state) && !original.acceptStates().contains(state)) {
                    return original.transitionFunction().apply(pair);
                } else if (original.acceptStates().contains(state) && symbol != NFA.EPSILON) {
                    return original.transitionFunction().apply(pair);
                } else if (original.acceptStates().contains(state) && symbol == NFA.EPSILON) {
                    Set<S> result = original.transitionFunction().apply(pair);
                    result.add(acceptState);
                    return result;
                } else {
                    return Set.of();
                }
            };

            Set<S> acceptStates = Collections.singleton(acceptState);

            return new NFA<>(states, original.alphabet(), transitionFunction, original.startState(), acceptStates);
        }
    }

    // maybe two type parameters (also in below methods)
    static <S> NFA<S> union(NFA<S> nfa1, NFA<S> nfa2) {
        S startState = null;

        Set<S> states = new HashSet<>(nfa1.states());
        states.addAll(nfa2.states());
        states.add(startState);

        assert nfa1.alphabet().equals(nfa2.alphabet());

        Function<Pair<S, Character>, Set<S>> transitionFunction = pair -> {
            S state = pair.first();
            char symbol = pair.second();

            if (nfa1.states().contains(state)) {
                return nfa1.transitionFunction().apply(pair);
            } else if (nfa2.states().contains(state)) {
                return nfa2.transitionFunction().apply(pair);
            } else if (state == startState && symbol == NFA.EPSILON) {
                return Set.of(nfa1.startState(), nfa2.startState());
            } else {
                return Set.of();
            }
        };

        Set<S> acceptStates = new HashSet<>(nfa1.acceptStates());
        acceptStates.addAll(nfa2.acceptStates());

        return new NFA<>(states, nfa1.alphabet(), transitionFunction, startState, acceptStates);
    }

    static <S> NFA<S> concatenation(NFA<S> nfa1, NFA<S> nfa2) {
        return null;
    }

    static <S> NFA<S> star(NFA<S> nfa) {
        return null;
    }
}