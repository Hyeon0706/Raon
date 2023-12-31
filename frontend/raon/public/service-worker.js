/* eslint-disable */
// install event
self.addEventListener('install', (e) => {
  e.waitUntil(
    caches.open('font-cache').then((cache) => {
      return cache.addAll([
        // 여기에 폰트 파일의 URL을 추가합니다.
        'https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2105_2@1.0/ONE-Mobile-POP.woff',
        'https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/CookieRun-Regular.woff',
        '//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebBold.eot',
        '//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebBold.eot?#iefix',
        // 필요한 다른 폰트 파일도 추가할 수 있습니다.
      ]);
    }),
  );
});

// activate event
self.addEventListener('activate', (e) => {
  // console.log('[Service Worker] actived', e);
});

// fetch event
self.addEventListener('fetch', (e) => {
  e.respondWith(
    caches.match(e.request).then((response) => {
      return response || fetch(e.request);
    }),
  );
});

// update event
self.addEventListener('message', (event) => {
  if (event.data && event.data.action === 'skipWaiting') {
    self.skipWaiting();
  }
});
