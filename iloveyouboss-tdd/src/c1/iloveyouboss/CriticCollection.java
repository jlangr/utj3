package iloveyouboss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public record CriticCollection(List<Critic<?>> criteria) {
   public CriticCollection(Critic<?>... criteria) {
      this(new ArrayList<>(Arrays.asList(criteria)));
   }

   public Stream<? extends Critic<?>> stream() {
      return criteria.stream();
   }

   public void add(Critic<?> criterion) {
      criteria.add(criterion);
   }
}
