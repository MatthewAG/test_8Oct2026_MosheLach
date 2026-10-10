package automata;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Function;

record NFA<S>(Set<S> states,
              Set<Character> alphabet,
              Function<Pair<S, Character>, Set<S>> transitionFunction,
              S startState,
              Set<S> acceptStates) {
    static final char EPSILON = 'ε';

    boolean accepts(String word) {
        Set<S> currentStates = statesReachableFrom(startState);

        for (char symbol : word.toCharArray()) {
            Set<S> newStates = new HashSet<>();

            for (S state : currentStates) {
                newStates.addAll(statesReachableFrom(transitionFunction.apply(new Pair<>(state, symbol))));
            }

            currentStates = newStates;
        }

        return !Collections.disjoint(currentStates, acceptStates);
    }

    DFA<Set<S>> toDFA() {
        Set<Set<S>> states = powerset(this.states);

        Function<Pair<Set<S>, Character>, Set<S>> transitionFunction = pair -> {
            Set<S> state = pair.first();
            char symbol = pair.second();
            Set<S> result = new HashSet<>();

            for (S nfaState : state) {
                result.addAll(statesReachableFrom(this.transitionFunction.apply(new Pair<>(nfaState, symbol))));
            }

            return result;
        };

        Set<S> startState = statesReachableFrom(this.startState);

        Set<Set<S>> acceptStates = new HashSet<>();
        for (Set<S> state : states) {
            if (!Collections.disjoint(state, this.acceptStates)) {
                acceptStates.add(state);
            }
        }

        return new DFA<>(states, alphabet, transitionFunction, startState, acceptStates);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("NFA: ").append(states).append(" ").append(alphabet).append(" [");
        StringJoiner joiner = new StringJoiner(", ");

        for (S state : states) {
            for (char symbol : alphabet) {
                Pair<S, Character> input = new Pair<>(state, symbol);
                Set<S> output = transitionFunction.apply(input);
                joiner.add(input + " -> " + output);
            }

            // epsilon
            Pair<S, Character> input = new Pair<>(state, EPSILON);
            Set<S> output = transitionFunction.apply(input);
            joiner.add(input + " -> " + output);
        }

        return sb.append(joiner).append("] ").append(startState).append(" ").append(acceptStates).toString();
    }

    private Set<S> statesReachableFrom(Set<S> states) {
        Set<S> result = new HashSet<>();

        for (S state : states) {
            result.addAll(statesReachableFrom(state));
        }

        return result;
    }

    private Set<S> statesReachableFrom(S state) {
        return Set.of(state);  // TODO dfs or bfs
    }

    private static <E> Set<Set<E>> powerset(Set<E> set) {
        Set<Set<E>> subsets = new HashSet<>();
        subsets.add(Set.of());

        for (E element : set) {
            for (Set<E> subset : subsets) {
                Set<E> copy = new HashSet<>(subset);
                copy.add(element);
                subsets.add(copy);
            }
        }

        return subsets;
    }
}