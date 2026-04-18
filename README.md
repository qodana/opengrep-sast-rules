# opengrep-sast-rules

A curated, redistribution-safe subset of SAST rules for [Opengrep](https://github.com/opengrep/opengrep) / Semgrep-compatible engines.

This repository contains **only rules that can be bundled into a commercial
product** — everything here is licensed under either **MIT** or **LGPL v3.0**.
Rules with incompatible licensing (Commons Clause, GitLab EE) have been
intentionally excluded.

## Origin

All rules were sourced from the upstream [GitLab SAST rules
repository](https://gitlab.com/gitlab-org/security-products/sast-rules)
(`github.com/gitlabhq/sast-rules`), which is itself an aggregation of rules
drawn from several well-known open-source SAST projects:

| Upstream project | Upstream license | Path in this repo |
| --- | --- | --- |
| [GitLab SAST rules](https://gitlab.com/gitlab-org/security-products/sast-rules) (MIT-licensed top-level content, © GitLab Inc.) | MIT (Expat) | `c/`, `csharp/`, `go/`, `java/`, `javascript/`, `python/`, `scala/` |
| [Find Security Bugs](https://find-sec-bugs.github.io/) | LGPL v3 | `rules/lgpl/kotlin/**` |
| [MobSF / mobsfscan](https://github.com/MobSF/mobsfscan) | LGPL v3 | `rules/lgpl/oc/**`, `rules/lgpl/swift/**`, `rules/lgpl/java/webview/**`, `rules/lgpl/kotlin/webview/**` |
| [njsscan](https://github.com/ajinabraham/njsscan) | LGPL v3 | `rules/lgpl/javascript/**` |

Per-file attribution (original upstream URL and license) is preserved in the
header comments of every `.yml` rule file under `rules/lgpl/`, e.g.:

```yaml
# yamllint disable
# License: GNU Lesser General Public License v3.0
# source (original): https://github.com/MobSF/mobsfscan/blob/main/...
# yamllint enable
```

## Repository layout

```
.
├── LICENSE                    # MIT — applies to top-level rule directories
├── README.md
├── c/           csharp/       # MIT rules (+ paired test cases)
├── go/          java/
├── javascript/  python/
├── scala/
└── rules/
    └── lgpl/                  # LGPL v3 rules (+ paired test cases)
        ├── LICENSE            # full LGPL v3 text
        ├── java/
        ├── javascript/
        ├── kotlin/
        ├── oc/
        └── swift/
```

Each rule file `rule-<name>.yml` is paired with a test-case file
`rule-<name>.<ext>` in the same directory (`.c`, `.py`, `.java`, `.kt`,
`.js`, `.scala`, `.cs`, `.go`, `.m`, `.swift`).

## License summary

| Path | License | Copyright | Use in a commercial product |
| --- | --- | --- | --- |
| `LICENSE` (root) and top-level language dirs (`c/`, `csharp/`, `go/`, `java/`, `javascript/`, `python/`, `scala/`) | MIT (Expat) | © 2011–present GitLab Inc. | Allowed. Retain the copyright notice and MIT license text in the product's third-party notices. |
| `rules/lgpl/**` | LGPL v3.0 | © respective upstream authors (Find Security Bugs, MobSF, njsscan, GitLab Inc. and contributors) | Allowed. See the compliance notes below. |

### LGPL v3 compliance notes

- Ship the full text of **LGPL v3** and **GPL v3** alongside the product
  (`rules/lgpl/LICENSE` already contains the LGPL v3 text; GPL v3 is
  incorporated by reference).
- Give prominent notice to the end user (for example in an "About",
  `THIRD_PARTY_LICENSES` or `NOTICES` file) that the product includes rules
  licensed under the LGPL v3 and identify the sources.
- Preserve the per-file header comments (`# License:`, `# source (original):`,
  `# hash:`) on every `.yml` file in `rules/lgpl/`. They are the upstream
  attribution.
- If you **modify** any rule in `rules/lgpl/`, carry the modifications under
  LGPL v3 as well, mark the files as changed (e.g. add a
  `# modified by <organization>, <date>` line), and make the modified
  sources available to your users (LGPL v3 §§ 4, 5).
- Since the rules are distributed as standalone `.yml` files and are not
  statically linked into your product, the "Combined Work" requirements of
  LGPL v3 § 4 are satisfied by simply allowing the user to replace the
  rule files.

## What was intentionally excluded

The following subdirectories of the upstream GitLab repository are **not**
present here, because they cannot be redistributed in a commercial product
without additional permission:

| Excluded upstream path | License | Reason |
| --- | --- | --- |
| `rules/lgpl-cc/` | LGPL 2.1 + **Commons Clause** (© Semgrep, Inc.) | Commons Clause prohibits "selling" a product whose value derives substantially from the software, which directly applies to a commercial SAST product. |
| `rules/gitlab/` | GitLab Enterprise Edition License (© GitLab B.V.) | Proprietary; production use requires a valid GitLab EE subscription and redistribution is forbidden. |

If you need coverage equivalent to those excluded rule sets in your
product, either obtain a commercial license from the respective vendors
or re-implement the rules independently.

## Contributing

When adding rules to this repository:

1. Confirm the upstream license is MIT, Apache-2.0, BSD, LGPL v3 or another
   license permitting commercial redistribution.
2. For LGPL v3 rules, place them under `rules/lgpl/<language>/` and include
   the standard header (`# License:` + `# source (original):`).
3. Provide a paired test-case file (`rule-<name>.<ext>`) demonstrating the
   pattern the rule is expected to match.
