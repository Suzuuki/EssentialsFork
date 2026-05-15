# Sync with upstream EssentialsX

This fork tracks https://github.com/EssentialsX/Essentials.git as `upstream`.
Your work goes to `origin` (https://github.com/Suzuuki/EssentialsFork.git).

## Remotes

```
origin    https://github.com/Suzuuki/EssentialsFork.git   (your fork — push here)
upstream  https://github.com/EssentialsX/Essentials.git   (read-only; push is disabled on purpose)
```

Default branch: `2.x`.

## Workflow

Make your changes on a topic branch off `2.x`, never directly on `2.x`. That keeps
`2.x` clean and identical to upstream, so pulling new upstream commits is always a
clean fast-forward.

```sh
git checkout 2.x
git checkout -b feature/my-change
# ... edit, commit ...
git push -u origin feature/my-change
```

## Pulling upstream changes into your fork

```sh
# 1. fetch upstream
git fetch upstream

# 2. fast-forward your local 2.x to match upstream
git checkout 2.x
git merge --ff-only upstream/2.x

# 3. push updated 2.x to your fork on GitHub
git push origin 2.x

# 4. rebase your topic branches on top of new 2.x
git checkout feature/my-change
git rebase 2.x
git push --force-with-lease origin feature/my-change
```

If `git merge --ff-only` fails, it means someone committed directly to your `2.x`.
Move those commits to a topic branch and reset `2.x` to `upstream/2.x`:

```sh
git checkout 2.x
git branch feature/recovered-work  # save the commits
git reset --hard upstream/2.x
```

## Why push to upstream is disabled

The upstream push URL is set to `DISABLED_PUSH_TO_UPSTREAM` so `git push upstream`
fails fast. This prevents accidentally pushing to the official EssentialsX repo.
To re-enable: `git remote set-url --push upstream https://github.com/EssentialsX/Essentials.git`
