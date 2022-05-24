package competitors;

public enum Discipline {

  Butterfly {
    @Override
    public String toString() {
      return "Butterfly";
    }
  },
  Crawl {
    @Override
    public String toString() {
      return "Crawl";
    }
  },
  BackCrawl {
    @Override
    public String toString() {
      return "BackCrawl";
    }
  },
  Breaststroke {
    @Override
    public String toString() {
      return "Breaststroke";
    }
  }
}

