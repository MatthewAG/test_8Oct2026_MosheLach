package automata;

import java.util.Set;
import java.util.StringJoiner;
import java.util.function.Function;

public record DFA<S>(Set<S> states,
                     Set<Character> alphabet,
                     Function<Pair<S, Character>, S> transitionFunction,
                     S startState,
                     Set<S> acceptStates) {
    public boolean accepts(String word) {
        S currentState = startState;

        for (char symbol : word.toCharArray()) {
            currentState = transitionFunction.apply(new Pair<>(currentState, symbol));
        }

        return acceptStates.contains(currentState);
    }

    NFA<S> toNFA() {
        Function<Pair<S, Character>, Set<S>> transitionFunction = pair -> Set.of(this.transitionFunction.apply(pair));
        return new NFA<>(states, alphabet, transitionFunction, startState, acceptStates);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DFA: ").append(states).append(" ").append(alphabet).append(" [");
        StringJoiner joiner = new StringJoiner(", ");

        for (S state : states) {
            for (char symbol : alphabet) {
                Pair<S, Character> input = new Pair<>(state, symbol);
                S output = transitionFunction.apply(input);
                joiner.add(input + " -> " + output);
            }
        }

        return sb.append(joiner).append("] ").append(startState).append(" ").append(acceptStates).toString();
    }
}