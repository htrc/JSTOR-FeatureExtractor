# JSTOR-Feature-Extractor

## Build (requires Java 8+)

`mvn package`

then find the runnable tools in `target/jstor-feature-extractor/`

## Run

```
Usage: jstor-feature-extractor [options]
  Options:
    -h, --help

    -i, --input
      Input JSONL (jsonlines.org) data to process (omit to read from stdin)
    -o, --output
      Output JSON document containing the extracted features (omit to output 
      to stdout)
```

To add custom Java options (for adjusting memory, etc.) use:
`JAVA_OPTS="-Xmx4g" /path/to/jstor-feature-extractor <arguments>`

## Input

The format required for the input JSONs:

```
{ 
  id: "the document id",
  pages: [
    {
       seq: "00000001",
       content: "page text content including line breaks"
    },
    {
      ...
    }
  ]
}
```

## Output

The output JSON has a structure similar to the following:

```json
{
  "id": "the document id",
  "features": {
    "pageCount" : 344,
    "pages" : [
      {
        "seq" : "000000001",
        "version" : "[some MD-5 hashed value]",
        "tokenCount" : 0,
        "lineCount" : 0,
        "emptyLineCount" : 0,
        "sentenceCount" : 0,
        "header" : {
          "tokenCount" : 0,
          "lineCount" : 0,
          "emptyLineCount" : 0,
          "sentenceCount" : 0,
          "capAlphaSeq" : 0,
          "beginCharCount" : {},
          "endCharCount" : {},
          "tokenPosCount" : {}
        },
        "body" : {
          "tokenCount" : 0,
          "lineCount" : 0,
          "emptyLineCount" : 0,
          "sentenceCount" : 0,
          "capAlphaSeq" : 0,
          "beginCharCount" : {},
          "endCharCount" : {},
          "tokenPosCount" : {}
        },
        "footer" : {
          "tokenCount" : 0,
          "lineCount" : 0,
          "emptyLineCount" : 0,
          "sentenceCount" : 0,
          "capAlphaSeq" : 0,
          "beginCharCount" : {},
          "endCharCount" : {},
          "tokenPosCount" : {}
        },
        "language" : null 
      },
      {
        "seq" : "000000002",
        "version" : "[some MD-5 hashed value]",
        "tokenCount" : 2,
        "lineCount" : 4,
        "emptyLineCount" : 2,
        "sentenceCount" : 2,
        "header" : {
          "tokenCount" : 0,
          "lineCount" : 0,
          "emptyLineCount" : 0,
          "sentenceCount" : 0,
          "capAlphaSeq" : 0,
          "beginCharCount" : {},
          "endCharCount" : {},
          "tokenPosCount" : {}
        },
        "body" : {
          "tokenCount" : 2,
          "lineCount" : 4,
          "emptyLineCount" : 2,
          "sentenceCount" : 2,
          "capAlphaSeq" : 0,
          "beginCharCount" : {
            "^" : 1,
            "<" : 1
          },
          "endCharCount" : {
            "&" : 1,
            "." : 1
          },
          "tokenPosCount" : {
            "<f**A*&": {"CC": 1},
            "^m**.": {".": 1}
          }
        },
        "footer" : {
          "tokenCount" : 0,
          "lineCount" : 0,
          "emptyLineCount" : 0,
          "sentenceCount" : 0,
          "capAlphaSeq" : 0,
          "beginCharCount" : {},
          "endCharCount" : {},
          "tokenPosCount" : {}
        },
        "language": "[the ISO 639-1 language code for the predominant page language]"
      },
      {
        "seq" : "00000343",
        "version" : "[some MD-5 hashed value]",
        "tokenCount" : 17,
        "lineCount" : 7,
        "emptyLineCount" : 2,
        "sentenceCount" : 1,
        "header" : {
          "tokenCount" : 0,
          "lineCount" : 0,
          "emptyLineCount" : 0,
          "sentenceCount" : 0,
          "capAlphaSeq" : 0,
          "beginCharCount" : {},
          "endCharCount" : {},
          "tokenPosCount" : {}
        },
        "body" : {
          "tokenCount" : 17,
          "lineCount" : 7,
          "emptyLineCount" : 2,
          "sentenceCount" : 1,
          "capAlphaSeq" : 2,
          "beginCharCount" : {
            "I": 1,
            "U": 1,
            "T": 2,
            "3": 1
          },
          "endCharCount" : {
            "9": 1,
            "N": 1,
            "t": 1,
            "p": 1,
            "0": 1
          },
          "tokenPosCount" : {
            "UNIVERSITY": {"NN": 1},
            "T": {"NNP": 1},
            "MICHIGAN": {"NNP": 1},
            "Plant": {"NNP": 1},
            "OF": {"IN": 1},
            "9015": {"CD": 1},
            "Indiana": {"NNP": 1},
            "The": {"DT": 1},
            "Group": {"NNP": 1},
            "17": {"CD": 1},
            "2": {"CD": 1},
            "00": {"CD": 1},
            "HF": {"NN": 1},
            "3": {"CD": 1},
            "072016": {"CD": 1},
            "5169": {"CD": 1},
            "06600": {"IN": 1}
          }
        },
        "footer" : {
          "tokenCount" : 0,
          "lineCount" : 0,
          "emptyLineCount" : 0,
          "sentenceCount" : 0,
          "capAlphaSeq" : 0,
          "beginCharCount" : {},
          "endCharCount" : {},
          "tokenPosCount" : {}
        },
        "language": "en"
      }
    ]
  }
}
```