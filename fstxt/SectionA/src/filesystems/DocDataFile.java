package filesystems;

import java.util.Arrays;
import java.util.Objects;

public final class DocDataFile extends DocFile {
  private final byte[] contents;

  public DocDataFile(String name, byte[] contents) {
    super(name);
    this.contents = contents;
  }


  @Override
  public int getSize() {
    return this.getName().length() + contents.length;
  }

  @Override
  public boolean isDirectory() {
    return false;
  }

  @Override
  public boolean isDataFile() {
    return true;
  }

  @Override
  public DocDirectory asDirectory() {
    throw new UnsupportedOperationException();
  }

  @Override
  public DocDataFile asDataFile() {
    return this;
  }

  @Override
  public DocFile duplicate() {
    return new DocDataFile(getName(), contents);
  }

  public boolean containsByte(byte inputByte) {
    for (byte b : contents) {
      if (b == inputByte) return true;
    }
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof DocDataFile)) {
      return false;
    }

    DocDataFile that = (DocDataFile) o;
    return Arrays.equals(contents, that.contents) && getName().equals(that.getName());
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(getName());
    result = 31 * result + Arrays.hashCode(contents);
    return result;
  }
}
