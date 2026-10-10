package automata.Matthew;

import automata.Pair;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public interface FA<State_Type, Character_Type> {
    class FA_Information<State_Type>
    {
        private Set<State_Type> Q; // states,
        private Set<Character_Type> Σ; // alphabet,
        private δ<Parenthesis<State_Type, Character_Type>, State_Type> δ; // Function<Pair<S, Character>, S> transitionFunction,
        private State_Type q; // startState,
        private Set<State_Type> F; // acceptStates

        public void set_Q(Set<State_Type> Q)
        {
            this.Q = new HashSet<State_Type>(Q);
        }
        public Set<State_Type> get_Q()
        {
            return(new HashSet<State_Type>(this.Q));
        }

        public void set_Σ(Set<Character_Type> Σ)
        {
            this.Σ = new HashSet<Character_Type>(Σ);
        }
        public Set<Character_Type> get_Σ()
        {
            return(new HashSet<Character_Type>(this.Σ));
        }

        public void set_δ(<Parenthesis<State_Type, Character_Type>, State_Type> δ)
        {
            this.δ = new δ<Parenthesis<State_Type, Character_Type>, State_Type>(δ);
        }
        public δ<Parenthesis<State_Type, Character_Type>, State_Type> get_δ()
        {
            return(new δ<Parenthesis<State_Type, Character_Type>, State_Type>(this.δ));
        }

        public void set_F(Set<State_Type> F)
        {
            this.F = new HashSet<State_Type>(F);
        }
        public Set<State_Type> get_F()
        {
            return(new HashSet<State_Type>(this.F));
        }

        public void set_q(State_Type q)
        {
            this.q = q;
        }
        public State_Type get_q()
        {
            return(this.q);
        }
    }

    class Parenthesis<ParameterOne_Type, ParameterTwo_Type> {
    }

    class δ<Input_Type, Output_Type> {
    }

}
/*
package automata.Matthew;

public interface FA {
}

*/
//package automata.Matthew;
//
//import automata.Pair;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.function.Function;
//
//public interface FA<State_Type> {
//    class FA_Information<State_Type>
//    {
//        private Set<State_Type> Q; // states,
//        private Set<Character> Σ; // alphabet,
//        private δ<Parenthesis<State_Type, Character>, State_Type> δ; // Function<Pair<S, Character>, S> transitionFunction,
//        private State_Type q; // startState,
//        private Set<State_Type> F; // acceptStates
//
//        public void set_Q(Set<State_Type> Q)
//        {
//            this.Q = new HashSet<State_Type>(Q);
//        }
//        public Set<State_Type> get_Q()
//        {
//            return(new HashSet<State_Type>(this.Q));
//        }
//
//        public void set_Σ(Set<Character> Σ)
//        {
//            this.Σ = new HashSet<Character>(Σ);
//        }
//        public Set<Character> get_Σ()
//        {
//            return(new HashSet<Character>(this.Σ));
//        }
//
//        public void set_δ(<Parenthesis<State_Type, Character>, State_Type> δ)
//        {
//            this.δ = new δ<Parenthesis<State_Type, Character>, State_Type>(δ);
//        }
//        public δ<Parenthesis<State_Type, Character>, State_Type> get_δ()
//        {
//            return(new δ<Parenthesis<State_Type, Character>, State_Type>(this.δ));
//        }
//
//        public void set_F(Set<State_Type> F)
//        {
//            this.F = new HashSet<State_Type>(F);
//        }
//        public Set<State_Type> get_F()
//        {
//            return(new HashSet<State_Type>(this.F));
//        }
//
//        public void set_q(State_Type q)
//        {
//            this.q = q;
//        }
//        public State_Type get_q()
//        {
//            return(this.q);
//        }
//    }
//
//}