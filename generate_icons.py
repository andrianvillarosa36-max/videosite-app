from PIL import Image
import os

SOURCE = os.path.expanduser('~/videosite-app/assets/icon.png')
RES_DIR = os.path.expanduser('~/videosite-app/android/app/src/main/res')

# (folder, pixel size) for each density
SIZES = {
    'mipmap-mdpi': 48,
    'mipmap-hdpi': 72,
    'mipmap-xhdpi': 96,
    'mipmap-xxhdpi': 144,
    'mipmap-xxxhdpi': 192,
}

img = Image.open(SOURCE).convert('RGBA')

for folder, size in SIZES.items():
    target_dir = os.path.join(RES_DIR, folder)
    resized = img.resize((size, size), Image.LANCZOS)
    resized.save(os.path.join(target_dir, 'ic_launcher.png'))
    resized.save(os.path.join(target_dir, 'ic_launcher_round.png'))
    resized.save(os.path.join(target_dir, 'ic_launcher_foreground.png'))
    print(f'Updated {folder} ({size}x{size})')

print('Done.')
